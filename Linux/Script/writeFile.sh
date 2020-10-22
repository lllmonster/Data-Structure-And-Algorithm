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

function append_file {
  cat <<EOT >> ${FILENAME}
appendline1
appendline2
append id ${input1}
EOT
}

create_new_file
append_file
