# Crime-Record
TLDR; Maps out local Pittsburgh crime details on Google Earth based on user queries.

This Java project utilizes _binary search trees_, _linked lists_, and _Keyhole Markup Language_ (KML) to allow for Google Earth Pro compatability (and to view the output of the program). 

The primary input in the program is the CSV file, which contains historical crime record data in the local Pittsburgh-CMU area that is read into a binary tree. Each tree node contains a linked list and is sorted by a unique street name identifier. 

You are able to enter three various queries to interpret this data meaningfully: 

1. Enter a street address for a specific crime report at this address.
2. Find all crimes at the most popular address for crimes.
3. Find all crimes within a user-specified distance from a crime address. 

A KML file is rewritten after each query. Placemark elements in the KML file allow for specific crime data to be displayed (for example, crime type).

