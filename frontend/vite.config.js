import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    host: true,
    strictPort:true,
    port:5173,
    proxy: {
      '/api': 'http://backend:8080'
    },
  },
})
