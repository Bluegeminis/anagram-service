#!/bin/bash

echo "What are anagrams of cat ?"
curl -s http://localhost:9000/anagrams/cat
--echo ""
echo "Are soap and soup anagrams ?"
curl -s http://localhost:9000/anagrams/soap/soup
echo ""
echo "Are nup and pun anagrams ?"
curl -s http://localhost:9000/anagrams/nup/pun