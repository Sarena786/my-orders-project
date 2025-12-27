<template>
  <div class="page">
    <div class="card">
      <h2>Login</h2>

      <input
          v-model="username"
          placeholder="username"
      />

      <input
          type="password"
          v-model="password"
          placeholder="password"
      />

      <button class="primary" @click="submit">
        Login
      </button>
      <button class="secondary" @click="goRegister">
        Register
      </button>
      <p v-if="error" class="error">{{ error }}</p>
      <p v-if="success" class="success">{{ success }}</p>
    </div>
  </div>
</template>

<script>
import { login } from "../services/auth.js";

export default {
  methods: {
    async submit() {
      const res = await login(this.username, this.password);
      localStorage.setItem("token", res.data);
      this.$router.push("/home");
    },
    goRegister() {
      this.$router.push("/register");
    }
  }
};
</script>

<style scoped>
/* full screen background */
.page {
  height: 100vh;
  background-image: url("../assets/bg5.jpg");
  background-size: cover;
  background-position: center;
  display: flex;
  align-items: center;
  justify-content: center;
}

/* card */
.card {
  background: rgba(245, 245, 245, 0.95);
  padding: 40px;
  width: 320px;
  border-radius: 24px;
  text-align: center;
  box-shadow: 0 10px 30px rgba(0,0,0,0.3);
}

/* title */
.card h2 {
  margin-bottom: 20px;
}

/* inputs */
.card input {
  width: 100%;
  padding: 12px;
  margin-bottom: 15px;
  border-radius: 20px;
  border: none;
  outline: none;
}

/* buttons */
.primary {
  width: 100%;
  padding: 12px;
  border-radius: 20px;
  border: none;
  background: #d08c87;
  color: white;
  font-weight: bold;
  margin-bottom: 10px;
  cursor: pointer;
}

.secondary {
  width: 100%;
  padding: 10px;
  border-radius: 20px;
  border: none;
  background: #d08c87;
  color: white;
  opacity: 0.85;
  cursor: pointer;
}

/* messages */
.error {
  color: red;
  margin-top: 10px;
}

.success {
  color: green;
  margin-top: 10px;
}
</style>