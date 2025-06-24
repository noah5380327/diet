<template>
  <div class="fullscreen text-center q-pa-md flex flex-center error-not-found">
    <div class="error-not-found-card">
      <h3>Oops...</h3>

      <div class="sub-text">We can't find that page.</div>

      <img :src="ErrorPng" alt="404-error" class="image" />

      <div class="action">
        <q-btn
          color="primary"
          unelevated
          :to="{ name: 'dashboard' }"
          label="Go Home"
          no-caps
        />
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import ErrorPng from 'src/assets/404-error.png';

defineOptions({
  name: 'ErrorNotFound',
});
</script>

<style scoped lang="scss">
.error-not-found {
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  background-image: url('src/assets/bg-error-not-found.jpg');
  .error-not-found-card {
    width: 40%;
    background: #ffffff;
    box-shadow: 0px 3px 4px 0px rgba(0, 0, 0, 0.03);
    border: 1px solid #f1f1f4;
    border-radius: 0.625rem;
    padding: 2rem 2.5rem;
    h3 {
      font-weight: 700;
      margin-bottom: 1rem;
      margin-top: 1.25rem;
    }
    .sub-text {
      color: #99a1b7;
    }
    .image {
      height: 300px;
    }
  }
}
</style>
