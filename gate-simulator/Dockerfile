#FROM node:8.16.2-alpine
#WORKDIR /opt/app
#COPY . .
#RUN npm install
#CMD ["npm", "start"]
#EXPOSE 9999
FROM node:lts-alpine3.12
WORKDIR /opt/app
COPY . .
RUN npm install
CMD ["npm", "start"]
EXPOSE 9999