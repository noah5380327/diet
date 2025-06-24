<template>
  <login-layout>
    <div class="content-title">
      <h3>Sign Up</h3>
    </div>
    <q-form @submit="onSubmit" class="q-gutter-md content-form">
      <q-input
        outlined
        v-model="username"
        label="Username"
        lazy-rules
        :rules="[(val) => (val && val.length > 0) || 'Username is required']"
      />

      <q-input
        outlined
        :type="isPwd ? 'password' : 'text'"
        v-model="password"
        label="Password"
        lazy-rules
        :rules="[
          (val) => (val && val.length > 0) || 'Password is required',
          (val) =>
            (val && val.length > 5) || 'Password must be at least 6 characters',
        ]"
      >
        <template v-slot:append>
          <q-icon
            :name="isPwd ? 'visibility_off' : 'visibility'"
            class="cursor-pointer"
            @click="isPwd = !isPwd"
          />
        </template>
      </q-input>

      <q-input
        outlined
        :type="isConfirmPwd ? 'password' : 'text'"
        v-model="confirmPassword"
        label="Confirm Password"
        lazy-rules
        :rules="[
          (val) => (val && val.length > 0) || 'Confirm password is required',
          (val) =>
            val === password || 'The password and its confirm are not the same',
        ]"
      >
        <template v-slot:append>
          <q-icon
            :name="isConfirmPwd ? 'visibility_off' : 'visibility'"
            class="cursor-pointer"
            @click="isConfirmPwd = !isConfirmPwd"
          />
        </template>
      </q-input>

      <q-toggle v-model="isCoach" label="I am a coach" class="term" />

      <div>
        <q-btn
          no-caps
          label="Sign Up"
          type="submit"
          color="primary"
          class="full-width"
        />
      </div>
    </q-form>
    <div class="content-signin">
      Already have an Account?&nbsp;&nbsp;
      <router-link :to="{ name: 'login' }" class="text-primary">
        Sign in
      </router-link>
    </div>
  </login-layout>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { useQuasar } from 'quasar';
import LoginLayout from 'layouts/LoginLayout.vue';
import { userRegister } from 'src/server/apis/user';

defineOptions({
  name: 'RegisterPage',
});

const router = useRouter();
const $q = useQuasar();

const username = ref<string>('');
const password = ref<string>('');
const confirmPassword = ref<string>('');
const isPwd = ref<boolean>(true);
const isConfirmPwd = ref<boolean>(true);
const isCoach = ref(false);

function onSubmit() {
  userRegister({
    username: username.value,
    password: password.value,
    isCoach: isCoach.value,
  }).then(() => {
    $q.notify({
      type: 'positive',
      message: 'sign up success',
    });
    router.push({
      name: 'login',
    });
  });
}
</script>

<style scoped lang="scss">
.content-title {
  display: flex;
  justify-content: center;
  h3 {
    font-size: 1.75rem;
    font-weight: 700;
    margin-bottom: 1rem;
  }
}
.content-form {
  width: 100%;
  .term {
    color: #4b5675;
    margin-top: 0;
    margin-left: 5px;
  }
}
.content-signin {
  display: flex;
  justify-content: center;
  margin-top: 40px;
  color: #99a1b7;
  font-weight: 500;
  font-size: 1rem;
}
</style>
