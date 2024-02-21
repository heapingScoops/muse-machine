# The Muse Machine
For the poetry-lover/AI-enthusiast (an evergrowing market), the Muse Machine: An AI-image-powered-poem-of-the-day application that retrieves a random poem from a massive database of both modern and classic poems, uses AI to summarize the poem, and then calls on DALL-E to generate a unique image based on the poem's contents. Each creation is unique and saved in a PostgreSQL database, with image hosting powered by Cloudinary. Logged in users can ponder their past creations in the Memories section. 


## Technology Stack
* Vue.js
* Java
* Spring Boot
* PostgreSQL

## Future Development
* Host front-end on a static site hosting provider like Netlify
* host back-end using AWS Lambda functions
* host PostgreSQL database with AWS RDS
* restrict logged-in users to 1 creation per 24 hours
* Admin access to view creations of all users
* Allow users to pick from a series of 4 cryptic cards with crude drawings, each of which will correspond to genres/themes of poems in the database and return a poem from that category
* Allow users to view the AI-generated summaries which created the images
* Allow users to change prompt generation parameters (temperature & length)
* An About Me page where The Muse Machine explains its mission and purpose in poem form

## Screenshots
![Home Screen](./poetry-api-project/screenshots/home-screen.png)
---
![Category Screen](./screenshots/meal-cat-screen.png)
---
![Meal Screen](./screenshots/meal-screen.png)
---
![Drawer](./screenshots/drawer.png)
---
![Faves](./screenshots/faves.png)

# muse-machine front

This template should help get you started developing with Vue 3 in Vite.

## Recommended IDE Setup

[VSCode](https://code.visualstudio.com/) + [Volar](https://marketplace.visualstudio.com/items?itemName=Vue.volar) (and disable Vetur) + [TypeScript Vue Plugin (Volar)](https://marketplace.visualstudio.com/items?itemName=Vue.vscode-typescript-vue-plugin).

## Customize configuration

See [Vite Configuration Reference](https://vitejs.dev/config/).

## Project Setup

```sh
npm install
```

### Compile and Hot-Reload for Development

```sh
npm run dev
```

### Compile and Minify for Production

```sh
npm run build
```
