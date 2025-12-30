<template>
  <div class="orders-page">
    <!-- NAV BAR -->
    <nav class="nav">
      <button class="nav-btn" @click="goHome">HOME</button>
      <button class="nav-btn" @click="goProduct">PRODUCT</button>
      <button class="nav-btn active">ORDER</button>
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
        <button class="add-btn" @click="createOrder">
          เพิ่มออเดอร์
        </button>
      </div>

      <!-- ORDER LIST -->
      <div class="list">
        <div
            class="order-card"
            v-for="o in filteredOrders"
            :key="o.id"
            @click="goOrderDetail(o.id)"
        >
          <div class="info">
            <p>Order : {{ o.orderName }}</p>
            <p>วันที่ : {{ formatDate(o.createdAt) }}</p>
          </div>

          <div class="actions">
            <span class="delete" @click.stop="deleteOrder(o.id)">
              ลบ
            </span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { getOrders, createOrder, deleteOrder } from "../services/order";

export default {
  data() {
    return {
      orders: [],
      search: ""
    };
  },

  async mounted() {
    await this.loadOrders();
  },

  computed: {
    filteredOrders() {
      return this.orders.filter(o =>
          o.orderName.toLowerCase().includes(this.search.toLowerCase())
      );
    }
  },

  methods: {
    async loadOrders() {
      const res = await getOrders();
      this.orders = res.data;
    },

    async createOrder() {
      const res = await createOrder({
        orderName: "ว/ด/บ"
      });

      // reload list
      await this.loadOrders();

      // go inside the order
      this.$router.push(`/orders/${res.data.id}`);
    },

    async deleteOrder(id) {
      if (!confirm("คุณต้องการลบออเดอร์นี้ใช่ไหม?")) return;
      await deleteOrder(id);
      await this.loadOrders();
    },

    goOrderDetail(id) {
      this.$router.push(`/orders/${id}`);
    },

    formatDate(date) {
      return new Date(date).toLocaleDateString("th-TH");
    },

    goHome() {
      this.$router.push("/home");
    },

    goProduct() {
      this.$router.push("/product");
    },

    logout() {
      localStorage.removeItem("token");
      this.$router.push("/login");
    }
  }
};
</script>

<style scoped>
/* ORDER CARD (same vibe as product-card) */
.order-card {
  display: flex;
  align-items: center;
  border: 1px solid #ccc;
  border-radius: 24px;
  padding: 16px;
  cursor: pointer;
  transition: all 0.15s ease;
}

.order-card:hover {
  background: rgba(77, 163, 255, 0.08);
  border-color: #4da3ff;
}

/* Order info text */
.order-card .info p {
  margin: 4px 0;
  font-weight: 500;
}

/* Actions */
.order-card .actions {
  display: flex;
  flex-direction: column;
  gap: 8px;
  text-align: right;
}

.order-card .delete {
  color: red;
  cursor: pointer;
  font-weight: 600;
}


</style>