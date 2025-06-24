<template>
  <login-layout>
    <div class="content-title">
      <h3>Sign In</h3>
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

      <div>
        <q-btn
          no-caps
          label="Sign In"
          type="submit"
          color="primary"
          class="full-width"
        />
      </div>
    </q-form>
    <div class="content-signup">
      Not a Member yet?&nbsp;&nbsp;
      <router-link :to="{ name: 'register' }" class="text-primary">
        Sign up
      </router-link>
    </div>
  </login-layout>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import LoginLayout from 'layouts/LoginLayout.vue';
import { useRootStore } from 'stores/root';
import { userLogin } from 'src/server/apis/user';

defineOptions({
  name: 'LoginPage',
});

const router = useRouter();
const store = useRootStore();
const { setToken, setRole } = store;

const username = ref<string>('');
const password = ref<string>('');
const isPwd = ref<boolean>(true);

function onSubmit() {
  userLogin({
    username: username.value,
    password: password.value,
  }).then((res) => {
    setToken(res.token);
    setRole(res.object.role);
    router.push({
      name: 'dashboard',
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
}
.content-signup {
  display: flex;
  justify-content: center;
  margin-top: 40px;
  color: #99a1b7;
  font-weight: 500;
  font-size: 1rem;
}
</style>
