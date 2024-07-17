import dotenv from 'dotenv'
import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

dotenv.config( {path: '../.env' })

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    host: true,
    strictPort:true,
    port:5173,
    proxy: {
      '/api': process.env.BACKEND_URL
    },
  },
})
