<template>
  <Header/>
  <section class="insight-section">
    <div class="commit-container">
      <div class="commit-info">
        <span class="circle">39</span>
        <div class="progress-bar-container">
          <div class="progress-bar"></div>
        </div>
      </div>
      <article class="commit-article">
        <img loading="lazy" src="https://cdn.builder.io/api/v1/image/assets/TEMP/a15a58212de51f3f8af173f0f65e334091b691385ab3ae8e623d83ef49bba2bc?apiKey=b979e3b43d464a49a8e4c392f6c7b6d1&" alt="Commitment Image" class="commit-image" />
        <div class="commit-details">
          <span class="commit-days">... Days of COMMITment</span>
        </div>
      </article>
    </div>
    <nav class="insight-navigation">Insights</nav>
  </section>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import Header from "./Header.vue";

export default defineComponent({
  name: 'InsightsComponent'
});
</script>

<style scoped>
.insight-section {
  display: flex;
  justify-content: space-between;
  gap: 1px;
  padding: 12px 28px;
  background-color: var(--1st, #5d5a5a);
}

@media (max-width: 991px) {
  .insight-section {
    flex-wrap: wrap;
    padding: 0 20px;
  }
}

.commit-container {
  display: flex;
  gap: 1px;
}

.commit-info {
  display: flex;
  gap: 0;
}

.circle {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 37px;
  height: 37px;
  padding: 0 10px;
  border-radius: 50%;
  background-color: #f5f5f5;
  color: #f5c800;
  font: 700 12px Inter, sans-serif;
}

@media (max-width: 991px) {
  .circle {
    white-space: initial;
  }
}

.progress-bar-container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  margin-top: 8px;
  background-color: #fff;
  border-radius: 26px;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
}

.progress-bar {
  height: 21px;
  background-color: #ffdd61;
  border-radius: 26px 0 0 26px;
}

.commit-article {
  display: flex;
  align-items: center;
  gap: 8px;
  padding: 0 17px;
  font-size: 12px;
  color: var(--FrameColor, #fff);
  font-weight: 400;
}

.commit-image {
  width: 29px;
  aspect-ratio: 0.75;
  object-fit: cover;
  object-position: center;
}

.commit-details {
  display: flex;
  align-items: center;
  font-family: Inter, sans-serif;
}

.commit-days, .commit-strong {
  font-weight: bold;
  color: #fff;
}

.commit-text {
  color: #fff;
}

.insight-navigation {
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 6px 18px;
  background-color: var(--Button, #6f6b6b);
  color: #fff;
  font: 400 24px Inter, sans-serif;
  border-radius: 11px;
}

@media (max-width: 991px) {
  .insight-navigation {
    white-space: initial;
  }
}
</style>
