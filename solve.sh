#!/usr/bin/env bash
## Build + Run the AoC Problem Solver. Passes day# problem# arguments to the main function

function usage {
  echo -e ""
  echo -e "Usage: $(basename $0) [day#] [problem#]"
  echo -e ""
  echo -e "EXAMPLES:"
  echo -e "  $(basename $0) 1 1"
  echo -e "    ** solve day 1 problem 1"
  echo -e ""
}

if [ $# -ne 2 ]
then
  usage
else
  ./gradlew run --args="$1 $2"
fi


