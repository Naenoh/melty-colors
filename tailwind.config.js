const colors = require('tailwindcss/colors')

module.exports = {
  purge: ["src/main/jte/**/*.jte"],
  darkMode: false, // or 'media' or 'class'
  theme: {
    extend: {
        spacing: {
            '120': '30rem',
            '128': '32rem',
            '144': '36rem'
        }
    }
  },
  variants: {
  },
  plugins: [],
}
