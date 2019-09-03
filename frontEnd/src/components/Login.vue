<template>
  <b-container class="mt-5">
    <b-form v-if="show" @submit="onSubmit">
      <b-form-group label="Email address:">
        <b-form-input
          v-model="form.email"
          type="email"
          required
          placeholder="Enter email"
        ></b-form-input>
      </b-form-group>
      <b-form-group label="Password">
        <b-form-input
          type="password"
          v-model="form.password"
          required
          placeholder="password"
        ></b-form-input>
      </b-form-group>
      <div class="text-right">
        <b-button type="submit" variant="success">로그인</b-button>
      </div>
    </b-form>
  </b-container>
</template>

<script>
  export default {
    data () {
      return {
        form: {
          email: '',
          password: ''
        },
        show: true
      }
    },
    methods: {
      onSubmit (event) {
        event.preventDefault()
        alert(JSON.stringify(this.form))
      }
    }
  }
</script>
