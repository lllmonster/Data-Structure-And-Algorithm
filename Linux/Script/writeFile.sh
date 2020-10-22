#!/bin/bash
input1=$1
FILENAME=$2

function create_new_file {
  cat >${FILENAME} <<EOL
testline1
testline2
id ${input1}
EOL
}

create_new_file
