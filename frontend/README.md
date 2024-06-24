# frontend

This is a Dashboard application frontend with Vue.js v3. The UI is accessible under http://localhost:80.

## Prerequisites

Install [Node.js](https://nodejs.org/en/) and ensure that the root folder of its installation is added to your PATH. You can check with this command: echo %PATH% (or echo $PATH on Linux / Git Bash).

## Development Setup

There are multiple possibilities, here a few examples:
- [VSCode](https://code.visualstudio.com/) + [Volar](https://marketplace.visualstudio.com/items?itemName=Vue.volar) (and disable Vetur) + [TypeScript Vue Plugin (Volar)](https://marketplace.visualstudio.com/items?itemName=Vue.vscode-typescript-vue-plugin).
- [WebStorm](https://www.jetbrains.com/webstorm/) (brings the required plugins natively)
- your favorite text editor + CLI

## Project Setup

Install the required dependencies with
```sh
npm install
```

Then you are good to go with
```sh
npm run dev
```

To deploy your code to production, you can compile and minify using
```sh
npm run build
```

Also, there is linter available:
```sh
npm run lint
```
## Usage

First you start the Backend with
```sh
./mvnw spring-boot:run
```

Then second you start the Frontend with (if you are in the right path, if not you iterate through with cd until you are in the Frontend Folder)
```sh
npm run dev
```

After that you can visit the Website at
```sh
http://localhost:5173
```
### Frontend Test
- Login Screen on http://localhost:80
- After you login with your Github-account you can view your projects in the Dashboard on http://localhost:80/dashboard
