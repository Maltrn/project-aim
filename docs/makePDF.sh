#!/bin/bash
pdflatex Pflichtenheft.tex
makeglossaries Pflichtenheft
pdflatex Pflichtenheft.tex
pdflatex Pflichtenheft.tex


