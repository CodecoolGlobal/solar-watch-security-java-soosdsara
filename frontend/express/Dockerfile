#Stage 1: Create the Express app
FROM node:19-alpine as vite-builder
WORKDIR /vite-app
COPY /frontend/package*.json ./
RUN npm install
COPY /frontend ./
RUN npm run build

#Stage 2: Create the Express app
FROM node:slim as express-builder
WORKDIR /app
COPY /frontend/express/package.json ./
RUN npm install
COPY /frontend/express ./
COPY --from=vite-builder /vite-app/dist /app/public
CMD ["node", "index.js"]
