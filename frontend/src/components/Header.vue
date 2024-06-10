<template>
  <div class="div">
    <div class="div-2">
      <img
        loading="lazy"
        srcSet="..."
        class="img"
      />
      <div class="div-3">toLogin.userName</div>
    </div>
    <div class="div-4">GitHub Dashboard</div>
    <div class="div-5">
      <div class="div-6">Managment View</div>
      <div class="div-7">
        <div class="div-8">Dark mode</div>
        <div class="div-9">Logout</div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.div {
  justify-content: space-between;
  align-items: center;
  background-color: var(--1st, #5d5a5a);
  display: flex;
  gap: 20px;
  font-size: 15px;
  color: #fff;
  font-weight: 400;
  padding: 10px 25px;
}
@media (max-width: 991px) {
  .div {
    flex-wrap: wrap;
    padding: 0 20px;
  }
}
.div-2 {
  justify-content: space-between;
  align-self: stretch;
  display: flex;
  gap: 14px;
}
.img {
  aspect-ratio: 1;
  object-fit: auto;
  object-position: center;
  width: 44px;
}
.div-3 {
  font-family: Inter, sans-serif;
  margin: auto 0;
}
.div-4 {
  align-self: stretch;
  margin: auto 0;
  font: 24px Inter, sans-serif;
}
.div-5 {
  align-self: stretch;
  display: flex;
  gap: 20px;
  justify-content: space-between;
  margin: auto 0;
  padding: 8px 0;
}
.div-6 {
  font-family: Inter, sans-serif;
}
.div-7 {
  display: flex;
  gap: 20px;
}
.div-8 {
  font-family: Inter, sans-serif;
}
.div-9 {
  font-family: Inter, sans-serif;
}
</style>
