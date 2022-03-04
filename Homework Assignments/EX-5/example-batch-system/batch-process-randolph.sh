#! /bin/sh 
for f in "$1"/*; do
  if [ -f "$f" ]; then
    SIZE="$(du -sh "${f}" | cut -f1)"
    LINES=`wc --lines < ${f}`
    echo "Processing $f file..."
    echo "File Size: $SIZE"
    echo "Number of Lines: $LINES"
  fi
done

