<template>
  <q-page class="q-pa-md flex flex-center">
    <q-card
      class="q-pa-lg shadow-2 rounded-borders"
      style="max-width: 600px; width: 100%"
    >
      <q-card-section>
        <div class="text-h6 text-center">Today's Menu</div>
      </q-card-section>

      <q-separator spaced />

      <q-card-section v-if="recipe">
        <div class="q-mb-sm">
          <strong>Total Calories：</strong>{{ recipe.totalCalories }} kcal
        </div>
        <div class="q-mb-sm">
          <strong>Breakfast：</strong>{{ recipe.breakfast }}
        </div>
        <div class="q-mb-sm"><strong>Lunch：</strong>{{ recipe.lunch }}</div>
        <div><strong>Dinner：</strong>{{ recipe.dinner }}</div>
      </q-card-section>

      <q-card-section v-else class="text-grey text-center">
        No recipe data.
      </q-card-section>

      <q-separator spaced />

      <q-card-actions align="center">
        <q-btn
          label="Generate recipe"
          color="primary"
          @click="onGenerateRecipe"
          :loading="loading"
        />
      </q-card-actions>
    </q-card>
  </q-page>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { useQuasar } from 'quasar';
import { getLatestRecipe, generateRecipe } from 'src/server/apis/recipe';
import { Recipe } from 'src/server/interfaces/recipe';

const $q = useQuasar();

const recipe = ref<Recipe>();
const loading = ref(false);

function initData() {
  getLatestRecipe().then((res) => {
    recipe.value = res;
  });
}

function onGenerateRecipe() {
  loading.value = true;
  generateRecipe()
    .then((res) => {
      $q.notify({ type: 'positive', message: 'generate success' });
      recipe.value = res;
    })
    .finally(() => {
      loading.value = false;
    });
}

onMounted(() => {
  initData();
});
</script>
