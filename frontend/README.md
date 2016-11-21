## project aim frontend

The frontend is partitioned in three parts. The upload center, the vondor page and the prodoct page. 
The different editable parts of the vendor and product are shown on the pages. The name is not editable. 
The editable parts are the short description, the long description, the facts table, the profile picture and the gallery. 
The upload center is for uploading files for the profile picture and the gallery. Its possible to upload different 
picture formats and pdfs. The profile picture shall be chosen from the uploaded files, but it´s only possible to choose 
a picture format. It´s possible to sort the files in the upload center.
This is based on Angular 2.0.

### Usage
- Make sure you have [node.js](https://nodejs.org/) installed version 5+
- Make sure you have NPM installed version 3+
- `WINDOWS ONLY` run `npm install -g webpack webpack-dev-server typescript` to install global dependencies
- run `npm install` to install dependencies
- run `npm start` to fire up dev server
- open browser to [`http://localhost:3000`](http://localhost:3000)
- if you want to use other port, open `package.json` file, then change port in `--port 3000` script
