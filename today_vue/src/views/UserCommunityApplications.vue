<template>
  <div class="reservation-container">
    <h1 class="title">预约查询</h1>

    <div class="input-section">
      <h2 class="subtitle">请输入学号</h2>
      <div class="input-group">
        <input
            v-model="studentId"
            type="text"
            placeholder="请输入您的学号"
            class="input-field"
            @keyup.enter="handleSearch"
        >
        <button @click="handleSearch" class="search-btn">查询</button>
      </div>
    </div>

    <div class="recent-queries" v-if="recentQueries.length > 0">
      <h3 class="recent-title">最近查询</h3>
      <ul class="query-list">
        <li
            v-for="query in recentQueries"
            :key="query"
            class="query-item"
            @click="selectRecentQuery(query)"
        >
          {{ query }}
        </li>
      </ul>
    </div>

    <div class="reservation-details" v-if="showReservationDetails">
      <div class="reservation-item">
        <input
            type="checkbox"
            id="confirmed"
            v-model="isConfirmed"
            class="checkbox"
        >
        <label for="confirmed">已确认</label>
      </div>

      <div class="reservation-item">
        <span class="reservation-id">#A12345</span>
      </div>

      <div class="reservation-item">
        <input
            type="checkbox"
            id="timeSlot"
            checked
            disabled
            class="checkbox"
        >
        <label for="timeSlot">2024-01-20 14:30-16:30</label>
      </div>

      <div class="reservation-item">
        <input
            type="checkbox"
            id="location"
            class="checkbox"
        >
        <label for="location">图书馆 - 自习室A</label>
      </div>

      <div class="reservation-item">
        <input
            type="checkbox"
            id="seat"
            class="checkbox"
        >
        <label for="seat">A区 - 座位12号</label>
      </div>
    </div>

    <div class="no-reservation" v-if="showNoReservation">
      没有找到预约记录
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';

const studentId = ref('');
const recentQueries = ref(['2021001234', '2021005678', '2021009012']);
const isConfirmed = ref(false);
const showReservationDetails = ref(false);
const showNoReservation = ref(false);

const handleSearch = () => {
  if (!studentId.value.trim()) return;

  // 模拟查询逻辑
  if (studentId.value === '2021001234') {
    showReservationDetails.value = true;
    showNoReservation.value = false;

    // 添加到最近查询（如果不存在）
    if (!recentQueries.value.includes(studentId.value)) {
      recentQueries.value.unshift(studentId.value);
      if (recentQueries.value.length > 5) {
        recentQueries.value.pop();
      }
    }
  } else {
    showReservationDetails.value = false;
    showNoReservation.value = true;
  }
};

const selectRecentQuery = (query) => {
  studentId.value = query;
  handleSearch();
};
</script>

<style scoped>
.reservation-container {
  max-width: 100%;
  padding: 20px;
  font-family: 'PingFang SC', 'Helvetica Neue', Arial, sans-serif;
  color: #333;
}

.title {
  font-size: 1.5rem;
  font-weight: bold;
  margin-bottom: 1.5rem;
  text-align: center;
  color: #2c3e50;
}

.subtitle {
  font-size: 1.2rem;
  margin-bottom: 1rem;
  color: #34495e;
}

.input-group {
  display: flex;
  margin-bottom: 1.5rem;
}

.input-field {
  flex: 1;
  padding: 12px 15px;
  border: 1px solid #ddd;
  border-radius: 8px 0 0 8px;
  font-size: 1rem;
  outline: none;
  transition: border 0.3s;
}

.input-field:focus {
  border-color: #3498db;
}

.search-btn {
  padding: 0 20px;
  background-color: #3498db;
  color: white;
  border: none;
  border-radius: 0 8px 8px 0;
  font-size: 1rem;
  cursor: pointer;
  transition: background-color 0.3s;
}

.search-btn:hover {
  background-color: #2980b9;
}

.recent-queries {
  margin-bottom: 1.5rem;
}

.recent-title {
  font-size: 1rem;
  color: #7f8c8d;
  margin-bottom: 0.5rem;
}

.query-list {
  list-style: none;
  padding: 0;
  margin: 0;
}

.query-item {
  padding: 10px 15px;
  background-color: #f8f9fa;
  border-radius: 6px;
  margin-bottom: 8px;
  cursor: pointer;
  transition: background-color 0.2s;
}

.query-item:hover {
  background-color: #e9ecef;
}

.reservation-details {
  background-color: white;
  border-radius: 10px;
  padding: 15px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  margin-top: 1rem;
}

.reservation-item {
  display: flex;
  align-items: center;
  padding: 10px 0;
  border-bottom: 1px solid #eee;
}

.reservation-item:last-child {
  border-bottom: none;
}

.checkbox {
  margin-right: 10px;
  width: 18px;
  height: 18px;
}

.reservation-id {
  font-weight: bold;
  color: #3498db;
}

.no-reservation {
  text-align: center;
  padding: 20px;
  color: #7f8c8d;
  font-size: 1rem;
}

/* 移动端适配 */
@media (max-width: 480px) {
  .reservation-container {
    padding: 15px;
  }

  .title {
    font-size: 1.3rem;
  }

  .subtitle {
    font-size: 1.1rem;
  }

  .input-field, .search-btn {
    padding: 10px 12px;
    font-size: 0.9rem;
  }

  .search-btn {
    padding: 0 15px;
  }
}
</style>
