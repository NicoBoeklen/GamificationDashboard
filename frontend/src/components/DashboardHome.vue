<template>
  <Header/>
  <section class="container">
    <header class="counter-wrapper">
      <div class="counter-box">
        <div class="counter-number">39</div>
        <div class="progress-bar-wrapper">
          <div class="progress-bar"></div>
        </div>
      </div>
      <div class="commitment-wrapper">
        <img loading="lazy" src="https://cdn.builder.io/api/v1/image/assets/TEMP/a15a58212de51f3f8af173f0f65e334091b691385ab3ae8e623d83ef49bba2bc?apiKey=b979e3b43d464a49a8e4c392f6c7b6d1&" class="commitment-logo" alt="Commitment Logo" />
        <p class="commitment-text"> <span class="strong-text">400</span> <span>Days of</span> <span class="bold-text">Commit</span> <span>ment</span> </p>
      </div>
    </header>
    <button class="insights-button">Insights</button>
  </section>
</template>
<style scoped>
.container {
  display: flex;
  justify-content: space-between;
  gap: 1px;
  padding: 12px 28px;
  background-color: var(--1st, #5d5a5a);
}
@media (max-width: 991px) {
  .container {
    flex-wrap: wrap;
    padding: 0 20px;
  }
}

.counter-wrapper {
  display: flex;
  gap: 1px;
}
.counter-box {
  display: flex;
  gap: 0;
}
.counter-number {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 37px;
  height: 37px;
  padding: 0 10px;
  border-radius: 262px;
  background-color: #f5f5f5;
  color: #f5c800;
  font: 700 12px Inter, sans-serif;
  white-space: nowrap;
}

@media (max-width: 991px) {
  .counter-number {
    white-space: normal;
  }
}
.progress-bar-wrapper {
  display: flex;
  flex-direction: column;
  justify-content: center;
  margin-top: 8px;
  border-radius: 26px;
  background-color: #fff;
  box-shadow: 0 4px 4px rgba(0, 0, 0, 0.25);
}
.progress-bar {
  width: 21px;
  height: 21px;
  border-radius: 26px 0 0 26px;
  background-color: #ffdd61;
}
.commitment-wrapper {
  display: flex;
  justify-content: center;
  gap: 8px;
  padding: 0 17px;
  font-size: 12px;
  font-weight: 400;
  color: var(--FrameColor, #fff);
}
.commitment-logo {
  width: 29px;
  aspect-ratio: 0.75;
  object-fit: cover;
  object-position: center;
}
.commitment-text {
  margin: auto 0;
  font-family: Inter, sans-serif;
}
.strong-text {
  font-weight: 700;
  color: rgba(255, 255, 255, 1);
}
.bold-text {
  font-weight: 800;
  color: rgba(255, 255, 255, 1);
}
.insights-button {
  padding: 6px 18px;
  border-radius: 11px;
  background-color: var(--Button, #6f6b6b);
  color: #fff;
  font: 400 24px Inter, sans-serif;
  white-space: nowrap;
}
@media (max-width: 991px) {
  .insights-button {
    white-space: normal;
  }
}
</style>
<script setup lang="ts">
import Header from "./Header.vue";
</script>
