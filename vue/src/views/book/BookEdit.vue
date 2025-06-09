<template>
  <book-add></book-add>
</template>

<script>
import BookAdd from './BookAdd.vue';

export default {
  name: 'BookEdit',
  components: {
    BookAdd
  }
};
</script>

<style scoped>
.book-edit {
  max-width: 500px;
  margin: 40px auto;
  background: #fff;
  padding: 24px;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0,0,0,0.08);
}
</style>
