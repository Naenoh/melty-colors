#!/bin/bash
DATE=$(date +%d-%m-%Y)
BACKUP_DIR=/home/daakhan/backup
cd /opt/projects/melty-colors/ || exit
tar -zcvpf $BACKUP_DIR/meltycolors-$DATE.tar.gz data
find $BACKUP_DIR/* -mtime +7 -exec rm {} \;