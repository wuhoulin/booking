<template>
  <div class="booking-form">
    <h2 class="section-title">预约信息</h2>

    <div class="form-group">
      <label for="activityName">活动名称（用途）<span class="required">*</span></label>
      <input
          id="activityName"
          v-model="formData.activityName"
          type="text"
          class="form-input"
          placeholder="请输入活动名称或用途"
          required
      />
    </div>

    <div class="form-group">
      <label for="department">申请部门<span class="required">*</span></label>
      <input
          id="department"
          v-model="formData.department"
          type="text"
          class="form-input"
          placeholder="个人/组织名称"
          required
          @input="updateParent"
      />
    </div>

    <div class="form-group">
      <label for="attendees">活动人数<span class="required">*</span></label>
      <div class="number-input-container">
        <input
            id="attendees"
            v-model.number="formData.attendees"
            type="number"
            class="form-input number-input"
            min="1"
            max="100"
            required
            @input="updateParent"
        />
       </div>
    </div>

    <div class="form-group checkbox-group">
      <label class="checkbox-label">
        <input
            type="checkbox"
            v-model="formData.needProjection"
            @change="updateParent"
        />
        <span>需要多媒体投屏</span>
      </label>
    </div>

    <div class="form-group">
      <label for="userName">使用人姓名<span class="required">*</span></label>
      <input
          id="userName"
          v-model="formData.userName"
          type="text"
          class="form-input"
          placeholder="请输入使用人姓名"
          required
          @input="updateParent"
      />
    </div>

    <div class="form-group">
      <label for="college">学院<span class="required">*</span></label>
      <input
          id="college"
          v-model="formData.college"
          type="text"
          class="form-input"
          placeholder="请输入所属学院"
          required
          @input="updateParent"
      />
    </div>

    <div class="form-group">
      <label for="major">年级专业<span class="required">*</span></label>
      <input
          id="major"
          v-model="formData.major"
          type="text"
          class="form-input"
          placeholder="请输入年级专业"
          required
          @input="updateParent"
      />
    </div>

    <div class="form-group">
      <label for="contact">联系方式<span class="required">*</span></label>
      <input
          id="contact"
          v-model="formData.contact"
          type="text"
          class="form-input"
          placeholder="请输入联系方式"
          required
          @input="updateParent"
      />
    </div>

    <div class="form-group">
      <label for="teacherName">指导老师</label>
      <input
          id="teacherName"
          v-model="formData.teacherName"
          type="text"
          class="form-input"
          placeholder="请输入指导老师姓名（如有）"
          @input="updateParent"
      />
    </div>

    <div class="form-group">
      <label for="teacherContact">指导老师联系方式</label>
      <input
          id="teacherContact"
          v-model="formData.teacherContact"
          type="text"
          class="form-input"
          placeholder="请输入指导老师联系方式（如有）"
          @input="updateParent"
      />
    </div>

    <div class="form-group">
      <label for="otherRequirements">其他需求</label>
      <textarea
          id="otherRequirements"
          v-model="formData.otherRequirements"
          class="form-textarea"
          placeholder="请输入其他需求（如有）"
          @input="updateParent"
      ></textarea>
    </div>
  </div>
</template>

<script setup>
import { ref, watch, computed } from 'vue';

const props = defineProps({
  modelValue: {
    type: Object,
    required: true,
    default: () => ({
      activityName: '',
      department: '',
      attendees: 1, // 新增的活动人数字段，默认1人
      needProjection: false,
      userName: '',
      college: '',
      major: '',
      contact: '',
      teacherName: '',
      teacherContact: '',
      otherRequirements: ''
    })
  }
});

const emit = defineEmits(['update:modelValue', 'formValidityChange']);

// 使用 computed 创建表单数据
const formData = computed({
  get: () => props.modelValue,
  set: (value) => {
    emit('update:modelValue', value);
    validateForm();
  }
});

// 增加人数
const incrementAttendees = () => {
  if (formData.value.attendees < 100) {
    formData.value.attendees++;
    updateParent();
  }
};

// 减少人数
const decrementAttendees = () => {
  if (formData.value.attendees > 1) {
    formData.value.attendees--;
    updateParent();
  }
};

// 立即验证表单
const validateForm = () => {
  const isValid =
      formData.value.activityName?.trim() !== '' &&
      formData.value.department?.trim() !== '' &&
      formData.value.attendees > 0 && // 验证人数大于0
      formData.value.userName?.trim() !== '' &&
      formData.value.college?.trim() !== '' &&
      formData.value.major?.trim() !== '' &&
      formData.value.contact?.trim() !== '';

  emit('formValidityChange', isValid);
};

// 更新父组件数据
const updateParent = () => {
  emit('update:modelValue', { ...formData.value });
  validateForm();
};

// 监听props变化，更新本地表单
watch(() => props.modelValue, (newValue) => {
  formData.value = { ...newValue };
}, { deep: true });

// 初始化验证
validateForm();
</script>

<style scoped>
.booking-form {
  margin-bottom: 28px;
  border-top: 1px solid #eaeaea;
  padding-top: 24px;
}

.section-title {
  font-size: 18px;
  font-weight: 600;
  margin-bottom: 16px;
  color: #333;
}

.form-group {
  margin-bottom: 16px;
}

.form-group label {
  display: block;
  margin-bottom: 8px;
  font-size: 14px;
  font-weight: 500;
  color: #333;
}

.required {
  color: #f5222d;
  margin-left: 4px;
}

.form-input, .form-textarea {
  width: 100%;
  padding: 12px 16px;
  border: 1px solid #d9d9d9;
  border-radius: 8px;
  font-size: 14px;
  transition: all 0.3s;
  background-color: #f9f9f9;
}

.form-input:focus, .form-textarea:focus {
  border-color: #1677ff;
  box-shadow: 0 0 0 2px rgba(22, 119, 255, 0.1);
  outline: none;
  background-color: #fff;
}

.form-textarea {
  min-height: 100px;
  resize: vertical;
}

.checkbox-group {
  margin-top: 8px;
  margin-bottom: 20px;
}

.checkbox-label {
  display: flex;
  align-items: center;
  cursor: pointer;
}

.checkbox-label input[type="checkbox"] {
  margin-right: 8px;
  width: 16px;
  height: 16px;
}

/* 新增的数字输入框样式 */
.number-input-container {
  display: flex;
  align-items: center;
}

.number-input {
  text-align: center;
  margin: 0 8px;
  width: 80px;
  -moz-appearance: textfield;
}

.number-input::-webkit-outer-spin-button,
.number-input::-webkit-inner-spin-button {
  -webkit-appearance: none;
  margin: 0;
}

.number-btn {
  width: 36px;
  height: 36px;
  border: 1px solid #d9d9d9;
  background-color: #f5f5f5;
  border-radius: 4px;
  font-size: 16px;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  transition: all 0.2s;
}

.number-btn:hover {
  background-color: #e6e6e6;
}

.number-btn:disabled {
  opacity: 0.5;
  cursor: not-allowed;
}
</style>
