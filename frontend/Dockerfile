# Etapa de build
FROM node:latest as build
WORKDIR /app
COPY . .
RUN npm install
RUN npm run build

# Etapa de execução
FROM nginx:alpine
COPY --from=build /app/dist /usr/share/nginx/html
COPY ./conf/nginx.conf /etc/nginx/nginx.conf
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]



