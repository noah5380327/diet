<template>
  <q-dialog v-model="modalVisible">
    <q-card class="table-form-container">
      <q-card-section class="row items-center q-pb-none">
        <div class="text-h6">Today's Menu</div>
        <q-space />
        <q-btn icon="close" flat round dense v-close-popup />
      </q-card-section>

      <q-card-section>
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
      </q-card-section>
    </q-card>
  </q-dialog>
</template>

<script setup lang="ts">
import { ref, computed, watch } from 'vue';
import { getRecipeByStudentId } from 'src/server/apis/recipe';
import { Recipe } from 'src/server/interfaces/recipe';

const recipe = ref<Recipe>();

interface ViewRecipeProps {
  visible: boolean;
  studentId: string;
}

interface ViewRecipeEmit {
  (e: 'update:visible', v: boolean): void;
}

const props = defineProps<ViewRecipeProps>();
const emit = defineEmits<ViewRecipeEmit>();

const modalVisible = computed({
  get: () => {
    return props.visible;
  },
  set: (newValue: boolean) => {
    emit('update:visible', newValue);
  },
});

function initData() {
  getRecipeByStudentId(props.studentId).then((res) => {
    recipe.value = res;
  });
}

watch(
  modalVisible,
  () => {
    if (modalVisible.value) {
      initData();
    }
  },
  {
    immediate: true,
  }
);
</script>
