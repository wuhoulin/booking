<template>
  <div class="reservation-container">
    <!-- 顶部导航栏 -->
    <div class="header">
      <div class="back-button">
        <i class="icon-back">&#10094;</i>
      </div>
      <div class="title">一站式社区预约</div>
      <div class="placeholder"></div>
    </div>

    <!-- 社区选择标签 -->
    <div class="community-tabs">
      <div
          v-for="(community, index) in communities"
          :key="index"
          class="tab"
          :class="{ active: activeCommunity === index }"
          @click="activeCommunity = index"
      >
        {{ community.name }}
      </div>
    </div>

    <!-- 位置和时间信息 -->
    <div class="info-section">
      <div class="info-item">
        <i class="icon-location"></i>
        <span>{{ communities[activeCommunity].location }}</span>
        <i class="icon-arrow-right"></i>
      </div>
      <div class="info-item">
        <i class="icon-time"></i>
        <span>开放时间 08:30 - 22:00</span>
        <i class="icon-arrow-right"></i>
      </div>
    </div>

    <!-- 搜索栏 -->
    <div class="search-bar">
      <i class="icon-search"></i>
      <input type="text" placeholder="搜索房间" v-model="searchQuery">
      <i class="icon-filter"></i>
    </div>

    <!-- 房间列表 -->
    <div class="room-list">
      <div
          v-for="(room, index) in filteredRooms"
          :key="index"
          class="room-card"
      >
        <div class="room-image" :style="{ backgroundImage: `url(${room.image})` }"></div>
        <div class="room-info">
          <div class="room-name">{{ room.name }}</div>
          <div class="room-capacity">可容纳 {{ room.capacity }}人</div>
        </div>
      </div>
    </div>

    <!-- 底部按钮 -->
    <div class="bottom-button">
      <button class="reserve-button">立即预约</button>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue';

// 社区数据
const communities = ref([
  {
    name: '学生第一社区',
    location: '位于1号楼和2号楼',
    rooms: [
      { name: '党团活动室', capacity: 80, image: '/placeholder.svg?height=200&width=400' },
      { name: '多功能研讨室1', capacity: 20, image: '/placeholder.svg?height=200&width=400' },
      { name: '多功能研讨室2', capacity: 15, image: '/placeholder.svg?height=200&width=400' },
      { name: '体育馆', capacity: 30, image: '/placeholder.svg?height=200&width=400' },
      { name: '心理咨询室', capacity: 20, image: '/placeholder.svg?height=200&width=400' },
      { name: '艺术室', capacity: 20, image: '/placeholder.svg?height=200&width=400' },
    ]
  },
  {
    name: '学生第二社区',
    location: '位于7号楼',
    rooms: [
      { name: '多功能活动室1', capacity: 30, image: '/placeholder.svg?height=200&width=400' },
      { name: '多功能活动室2', capacity: 30, image: '/placeholder.svg?height=200&width=400' },
      { name: '大厅心理聊吧', capacity: 40, image: '/placeholder.svg?height=200&width=400' },
      { name: '第一课室（体操馆）', capacity: 30, image: '/placeholder.svg?height=200&width=400' },
    ]
  },
  {
    name: '学生第三社区',
    location: '位于9号楼',
    rooms: [
      { name: '党团活动室', capacity: 80, image: '/placeholder.svg?height=200&width=400' },
      { name: '创新创优室', capacity: 30, image: '/placeholder.svg?height=200&width=400' },
    ]
  }
]);

// 当前选中的社区
const activeCommunity = ref(0);

// 搜索查询
const searchQuery = ref('');

// 过滤后的房间列表
const filteredRooms = computed(() => {
  const rooms = communities.value[activeCommunity.value].rooms;
  if (!searchQuery.value) return rooms;

  return rooms.filter(room =>
      room.name.toLowerCase().includes(searchQuery.value.toLowerCase())
  );
});
</script>

<style scoped>
.reservation-container {
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Helvetica, Arial, sans-serif;
  max-width: 100%;
  background-color: #f5f7fa;
  min-height: 100vh;
  padding-bottom: 80px;
  position: relative;
}

/* 顶部导航栏 */
.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 16px;
  background-color: #fff;
  position: sticky;
  top: 0;
  z-index: 10;
}

.back-button {
  width: 24px;
  height: 24px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.title {
  font-size: 18px;
  font-weight: 600;
}

.placeholder {
  width: 24px;
}

/* 社区选择标签 */
.community-tabs {
  display: flex;
  background-color: #fff;
  padding: 0 16px;
  border-bottom: 1px solid #eee;
}

.tab {
  padding: 12px 16px;
  font-size: 15px;
  color: #666;
  position: relative;
  white-space: nowrap;
}

.tab.active {
  color: #1677ff;
  font-weight: 500;
}

.tab.active::after {
  content: '';
  position: absolute;
  bottom: 0;
  left: 50%;
  transform: translateX(-50%);
  width: 20px;
  height: 3px;
  background-color: #1677ff;
  border-radius: 2px;
}

/* 位置和时间信息 */
.info-section {
  background-color: #fff;
  margin-bottom: 10px;
}

.info-item {
  display: flex;
  align-items: center;
  padding: 12px 16px;
  border-bottom: 1px solid #f0f0f0;
}

.info-item i {
  margin-right: 8px;
  color: #999;
}

.icon-location::before {
  content: '📍';
}

.icon-time::before {
  content: '🕒';
}

.icon-arrow-right {
  margin-left: auto;
}

.icon-arrow-right::before {
  content: '›';
  font-size: 20px;
  color: #ccc;
}

/* 搜索栏 */
.search-bar {
  display: flex;
  align-items: center;
  background-color: #fff;
  margin: 10px 16px;
  padding: 8px 12px;
  border-radius: 8px;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.05);
}

.icon-search::before {
  content: '🔍';
  font-size: 16px;
  color: #999;
}

.search-bar input {
  flex: 1;
  border: none;
  padding: 8px;
  font-size: 14px;
  outline: none;
}

.icon-filter::before {
  content: '≡';
  font-size: 20px;
  color: #999;
}

/* 房间列表 */
.room-list {
  padding: 0 16px;
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 16px;
}

.room-card {
  background-color: #fff;
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
}

.room-image {
  height: 120px;
  background-size: cover;
  background-position: center;
}

.room-info {
  padding: 12px;
}

.room-name {
  font-weight: 600;
  font-size: 15px;
  margin-bottom: 4px;
}

.room-capacity {
  font-size: 12px;
  color: #666;
  margin-bottom: 8px;
}

/* 底部按钮 */
.bottom-button {
  position: fixed;
  bottom: 0;
  left: 0;
  right: 0;
  padding: 16px;
  background-color: #fff;
  box-shadow: 0 -2px 10px rgba(0, 0, 0, 0.05);
}

.reserve-button {
  width: 100%;
  background-color: #1677ff;
  color: white;
  border: none;
  border-radius: 8px;
  padding: 12px;
  font-size: 16px;
  font-weight: 500;
  cursor: pointer;
}
</style>
