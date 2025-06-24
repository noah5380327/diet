<template>
  <q-layout view="lHh Lpr lFf" class="bg-grey-2">
    <q-header elevated class="bg-white text-grey-9">
      <q-toolbar>
        <q-btn
          flat
          dense
          round
          icon="menu"
          aria-label="Menu"
          @click="toggleLeftDrawer"
        />

        <q-toolbar-title class="flex items-center">
          <img
            src="/favicon.ico"
            alt="logo"
            width="38px"
            class="q-mr-sm cursor-pointer"
            @click="() => $router.push({ name: 'dashboard' })"
          />
          Diet
        </q-toolbar-title>

        <div class="flex items-center">
          {{ username }}
          <q-icon
            name="logout"
            size="20px"
            class="q-ml-lg cursor-pointer"
            @click="onLogout"
          />
        </div>
      </q-toolbar>
    </q-header>

    <q-drawer v-model="leftDrawerOpen" bordered class="bg-grey-10 text-white">
      <q-list>
        <q-item-label header class="text-white">Menus</q-item-label>

        <q-item
          v-for="(item, index) in menus"
          :key="index"
          clickable
          :active="$route.fullPath.indexOf(item.link) > -1"
          :to="{ name: item.link }"
        >
          <q-item-section v-if="item.icon" avatar>
            <q-icon :name="item.icon" />
          </q-item-section>

          <q-item-section>
            <q-item-label>{{ item.title }}</q-item-label>
          </q-item-section>
        </q-item>
      </q-list>
    </q-drawer>

    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script setup lang="ts">
import { ref, computed, onMounted } from 'vue';
import { useQuasar } from 'quasar';
import { useRouter } from 'vue-router';
import { storeToRefs } from 'pinia';
import { useRootStore } from 'stores/root';
import { getCurrentUser } from 'src/server/apis/user';
import { User } from 'src/server/interfaces/user';

export interface EssentialLinkProps {
  title: string;
  link: string;
  icon: string;
}

defineOptions({
  name: 'MainLayout',
});

const $q = useQuasar();
const router = useRouter();
const store = useRootStore();
const { isCoach } = storeToRefs(store);
const { setToken } = store;

const leftDrawerOpen = ref(true);
const currentUser = ref<User>();

const username = computed(() =>
  currentUser.value ? currentUser.value?.username : 'Quasar'
);
const menus = computed<EssentialLinkProps[]>(() => {
  let values: EssentialLinkProps[] = [
    {
      title: 'Home',
      icon: 'home',
      link: 'dashboard',
    },
    {
      title: 'Profile',
      icon: 'person',
      link: 'profile',
    },
    {
      title: 'Select Coach',
      icon: 'group',
      link: 'selectCoach',
    },
    {
      title: 'Exercise Record',
      icon: 'fitness_center',
      link: 'exerciseRecord',
    },
    {
      title: 'View Recipe',
      icon: 'restaurant_menu',
      link: 'recipeResult',
    },
  ];

  if (isCoach.value) {
    values = [
      {
        title: 'Home',
        icon: 'home',
        link: 'dashboard',
      },
      {
        title: 'Binding Request',
        icon: 'inbox',
        link: 'coachRequest',
      },
    ];
  }

  return values;
});

function toggleLeftDrawer() {
  leftDrawerOpen.value = !leftDrawerOpen.value;
}

function onLogout() {
  $q.dialog({
    title: 'Logout',
    message: 'Are you sure you want to exit the system?',
    cancel: true,
    persistent: true,
  }).onOk(() => {
    setToken('');
    router.push({
      name: 'login',
    });
  });
}

function initData() {
  getCurrentUser().then((res) => {
    currentUser.value = res;
  });
}

onMounted(() => {
  initData();
});
</script>
