FROM node:lts 

WORKDIR /client

COPY ["package.json", "package-lock.json*", "./"]

COPY . .

RUN npm install

CMD ["npm", "start" ]