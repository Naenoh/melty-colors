#!/bin/bash
DATE=$(date +%d-%m-%Y)
BACKUP_DIR=”/home/daakhan/backup”
tar -zcvpf $BACKUP_DIR/meltycolors-$DATE.tar.gz /opt/projects/melty-colors/data
find $BACKUP_DIR/* -mtime +7 -exec rm {} \;