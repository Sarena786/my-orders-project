<template>
  <div class="home">
    <nav class="nav">
      <button class="nav-btn active">HOME</button>
      <button class="nav-btn">PRODUCTS</button>
      <button class="nav-btn">ORDER</button>
      <button class="nav-btn logout" @click="logout">
        LOGOUT
      </button>
    </nav>
  </div>
</template>

<script>
export default {
  methods: {
    logout() {
      localStorage.removeItem("token");
      this.$router.push("/login");
    }
  }
};
</script>

<style scoped>
.home {
  height: 100vh;
  width: 100vw;
  background-image: url("../assets/bg2.jpg");
  background-size: cover;
  background-position: center;
  position: relative;
}

.nav {
  position: absolute;
  top: 40px;
  left: 70%;
  transform: translateX(-60%);
  display: flex;
  gap: 80px;
}

.nav-btn {
  padding: 10px 40px;
  border-radius: 999px;
  border: none;
  background: rgba(208, 140, 135, 0.85);
  color: black;
  font-weight: 600;
  cursor: pointer;
  transition: all 0.2s ease;
}

.nav-btn:hover {
  background: rgba(208, 140, 135, 1);
}

.nav-btn.logout {
  background: rgba(200, 80, 80, 0.85);
  color: white;
}

.nav-btn.logout:hover {
  background: rgba(200, 80, 80, 1);
}

.nav-btn.active {
  background: cornflowerblue;
  color: white;
}
</style>
