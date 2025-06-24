<template>
  <q-page class="q-pa-md">
    <div class="text-h5 text-center q-mb-md">{{ name }} Home</div>

    <div class="row q-col-gutter-md justify-center">
      <q-col
        v-for="card in cards"
        :key="card.title"
        cols="12"
        sm="6"
        md="6"
        class="q-mb-md"
      >
        <q-card
          class="cursor-pointer hoverable-card full-height q-pa-md"
          @click="() => $router.push({ name: card.route })"
        >
          <q-card-section class="text-center q-pa-lg">
            <q-icon :name="card.icon" size="36px" color="primary" />
            <div class="text-subtitle1 q-mt-sm">{{ card.title }}</div>
            <div
              class="text-caption text-grey q-mt-xs"
              style="min-height: 40px"
            >
              {{ card.subtitle }}
            </div>
          </q-card-section>
        </q-card>
      </q-col>
    </div>
  </q-page>
</template>

<script setup lang="ts">
import { computed } from 'vue';
import { storeToRefs } from 'pinia';
import { useRootStore } from 'stores/root';

defineOptions({
  name: 'DashboardPage',
});

const store = useRootStore();
const { isCoach } = storeToRefs(store);

const name = computed(() => {
  return isCoach.value ? 'Coach' : 'Student';
});
const cards = computed(() => {
  if (isCoach.value) {
    return [
      {
        title: 'Binding Request',
        subtitle: 'Review and process student requests',
        icon: 'inbox',
        route: 'coachRequest',
      },
      // {
      //   title: '学生列表',
      //   subtitle: '查看所有已绑定学生',
      //   icon: 'people',
      //   route: 'coachStudents'
      // },
      // {
      //   title: '训练记录',
      //   subtitle: '为学生上传训练数据',
      //   icon: 'fitness_center',
      //   route: 'uploadExercise'
      // },
      // {
      //   title: '配餐方案',
      //   subtitle: '查看学生的每日配餐',
      //   icon: 'restaurant_menu',
      //   route: 'viewStudentRecipe'
      // }
    ];
  }

  return [
    {
      title: 'Personal Profile',
      subtitle: 'View/Edit Your fitness information',
      icon: 'person',
      route: 'profile',
    },
    {
      title: 'Select Coach',
      subtitle: 'Select and Bind your Coach',
      icon: 'group',
      route: 'selectCoach',
    },
    {
      title: 'Add Exercise Record',
      subtitle: 'Record exercise and calculate consumption',
      icon: 'fitness_center',
      route: 'exerciseRecord',
    },
    {
      title: 'View Recipe',
      subtitle: 'Recommended three meals a day',
      icon: 'restaurant_menu',
      route: 'recipeResult',
    },
  ];
});
</script>

<style scoped lang="scss">
.hoverable-card {
  transition: transform 0.2s;
}
.hoverable-card:hover {
  transform: scale(1.03);
}
</style>
