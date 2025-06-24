<template>
  <div class="fullscreen q-pa-md login-layout">
    <div class="flex flex-center title-container">
      <div class="title-card">
        <div class="icon-container">
          <img src="/favicon.ico" alt="logo" class="image" />
          <h5>Diet</h5>
        </div>
        <div class="description-container">
          A web application for a diet app
        </div>
      </div>
    </div>
    <div class="flex flex-center content-container">
      <div class="content-card">
        <slot />
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
defineOptions({
  name: 'LoginLayout',
});
</script>

<style scoped lang="scss">
.login-layout {
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  background-image: url('src/assets/bg-login-page.jpg');
  display: flex;
  flex-direction: column;
  align-items: center;
  .title-container {
    width: 100%;
    margin-top: 3.75rem;
    .title-card {
      width: 70%;
      display: flex;
      flex-direction: column;
      align-items: center;
      .icon-container {
        display: flex;
        align-items: center;
        .image {
          height: 40px;
        }
        h5 {
          margin: 0;
          margin-left: 10px;
          color: #ffffff;
          font-weight: 700;
        }
      }
      .description-container {
        margin-top: 1rem;
        display: flex;
        color: #ffffff;
        font-weight: 400;
        font-size: 1.4rem;
      }
    }
  }
  .content-container {
    width: 80%;
    padding: 5rem;
    .content-card {
      width: 100%;
      background: #ffffff;
      border-radius: 1rem;
      padding: 3rem;
    }
  }
}

@media (min-width: $screen-xl) {
  .login-layout {
    flex-direction: row;
    .title-container {
      width: 50%;
      margin-top: 0;
      .title-card {
        align-items: flex-start;
      }
    }
    .content-container {
      width: 50%;
    }
  }
}

@media (max-width: $screen-xs) {
  .login-layout {
    .title-container {
      .title-card {
        width: 100%;
      }
    }
    .content-container {
      width: 100%;
      padding: 5rem 0;
    }
  }
}
</style>
