// Name: Christopher Miller
// Student ID: 20118392

#ifndef LISTFUNCS_H
#define LISTFUNCS_H

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define MAX_NAME_LEN 50

typedef struct PCB {
    int pid;
    char name[MAX_NAME_LEN];
} PCB;

typedef struct List_item {
    PCB *process;
    struct List_item *next;
} List_item;

int insert(List_item *listHead, List_item *insertItem);
int delete(List_item *listHead, int pid);
void printList(List_item *listHead);

#endif // LISTFUNCS_H
