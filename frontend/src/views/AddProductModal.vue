<template>
  <div class="overlay">
    <div class="modal">
      <h2>เพิ่มสินค้า</h2>

      <input v-model="form.name" placeholder="ชื่อสินค้า" />
      <input type="number" v-model="form.pricePerKg" placeholder="ราคา/กิโลกรัม" />
      <input type="number" v-model="form.quantity" placeholder="จำนวน (กก.)" />
      <input v-model="form.note" placeholder="หมายเหตุ" />
      <input v-model="form.imageUrl" placeholder="Image URL" />

      <div class="actions">
        <button class="cancel" @click="$emit('close')">ยกเลิก</button>
        <button class="save" @click="submit">บันทึก</button>
      </div>
    </div>
  </div>
</template>

<script>
import { addProduct } from "../services/product";

export default {
  emits: ["close", "saved"],
  data() {
    return {
      form: {
        name: "",
        pricePerKg: "",
        quantity: "",
        note: "",
        imageUrl: ""
      }
    };
  },
  methods: {
    async submit() {
      await addProduct(this.form);
      this.$emit("saved");   // tell parent to refresh list
      this.$emit("close");   // close modal
    }
  }
};
</script>

<style scoped>
.overlay {
  position: fixed;
  inset: 0;
  background: rgba(0,0,0,0.5);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 1000;
}

.modal {
  background: white;
  padding: 30px;
  width: 320px;
  border-radius: 20px;
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.modal input {
  padding: 10px;
  border-radius: 12px;
  border: 1px solid #ccc;
}

.actions {
  display: flex;
  justify-content: space-between;
  margin-top: 10px;
}

.cancel {
  background: #aaa;
  border: none;
  padding: 8px 16px;
  border-radius: 12px;
}

.save {
  background: #d08c87;
  color: white;
  border: none;
  padding: 8px 16px;
  border-radius: 12px;
}
</style>
