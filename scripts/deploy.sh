#!/bin/bash
git pull
npm run buildcss-prod
systemctl stop meltycolors
mvn package
systemctl start meltycolors