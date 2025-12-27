<template>
  <div class="products-page">
    <!-- NAV BAR -->
    <nav class="nav">
      <button class="nav-btn" @click="goHome">HOME</button>
      <button class="nav-btn active">PRODUCT</button>
      <button class="nav-btn">ORDER</button>
      <button class="nav-btn logout" @click="logout">LOGOUT</button>
    </nav>

    <!-- MAIN WHITE PANEL -->
    <div class="panel">
      <!-- TOP CONTROLS -->
      <div class="controls">
        <input
            class="search"
            placeholder="ค้นหา"
            v-model="search"
        />
        <button class="add-btn" @click="showAddModal = true">
          เพิ่มสินค้า
        </button>
        <AddProductModal
            v-if="showAddModal"
            @close="showAddModal = false"
            @saved="loadProducts"
        />
      </div>

      <!-- PRODUCT LIST -->
      <div class="list">
        <div class="product-card" v-for="p in filteredProducts" :key="p.id">
          <img :src="p.image" class="product-img" />

          <div class="info">
            <p>รายการ : {{ p.name }}</p>
            <p>ราคา/กิโลกรัม : {{ p.price }} บาท</p>
            <p>จำนวน : {{ p.quantity }} กก.</p>
            <p>หมายเหตุ : {{ p.note }}</p>
          </div>

          <div class="actions">
            <span class="edit">แก้ไข</span>
            <span class="delete">ลบ</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>


<script>
import AddProductModal from "../components/AddProductModal.vue";
import { getProducts } from "../services/product";

export default {
  components: {
    AddProductModal
  },

  data() {
    return {
      products: [],
      search: "",
      showAddModal: false
    };
  },

  async mounted() {
    await this.loadProducts();
  },

  methods: {
    async loadProducts() {
      const res = await getProducts();
      this.products = res.data;
    },
    goHome() {
      this.$router.push("/home");
    },
    logout() {
      localStorage.removeItem("token");
      this.$router.push("/login");
    }
  }
};
</script>

<style scoped>
.products-page {
  min-height: 100vh;
  background-image: url("../assets/bg5.jpg");
  background-size: cover;
  background-position: left center;
  padding-top: 20px;
}

/* NAV BAR */
.nav {
  position: absolute;
  top: 50px;
  left: 50%;
  transform: translateX(-50%);
  display: flex;
  gap: 80px;
}

.nav-btn {
  padding: 20px 40px;
  border-radius: 999px;
  border: none;
  background: rgba(208, 140, 135, 0.85);
  color: black;
  font-weight: 800;
  cursor: pointer;
  transition: all 0.2s ease;
}

.nav-btn.active {
  background: #4da3ff;
  color: white;
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


/* WHITE PANEL */
.panel {
  position: relative;
  top: 80px;
  margin: 40px auto;
  background: rgba(255, 255, 255, 0.95);
  width: 600px;
  padding: 30px;
  border-radius: 28px;
}

/* CONTROLS */
.controls {
  display: flex;
  justify-content: space-between;
  margin-bottom: 20px;
}

.search {
  flex: 1;
  padding: 10px 16px;
  border-radius: 999px;
  border: 1px solid #ccc;
  margin-right: 12px;
}

.add-btn {
  padding: 10px 20px;
  border-radius: 999px;
  border: none;
  background: #d08c87;
  color: white;
  cursor: pointer;
}

/* PRODUCT LIST */
.list {
  display: flex;
  flex-direction: column;
  gap: 16px;
}

.product-card {
  display: flex;
  align-items: center;
  border: 1px solid #ccc;
  border-radius: 24px;
  padding: 16px;
}

/* IMAGE */
.product-img {
  width: 90px;
  height: 90px;
  border-radius: 16px;
  object-fit: cover;
  margin-right: 16px;
}

/* INFO */
.info {
  flex: 1;
}

/* ACTIONS */
.actions {
  display: flex;
  flex-direction: column;
  gap: 8px;
  text-align: right;
}

.edit {
  color: #e67e22;
  cursor: pointer;
}

.delete {
  color: red;
  cursor: pointer;
}

</style>
