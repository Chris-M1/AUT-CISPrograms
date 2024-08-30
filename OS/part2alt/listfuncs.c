// Name: Christopher Miller
// Student ID: 20118392
#include "listfuncs.h"

int insert(List_item *listHead, List_item *insertItem) {
    List_item *current = listHead;
    while (current->next != NULL && current->next->process->pid < insertItem->process->pid) {
        current = current->next;
    }
    if (current->next != NULL && current->next->process->pid == insertItem->process->pid) {
        // Duplicate PID found, insertion unsuccessful
        return 0;
    }
    // Insert the new item into the list
    insertItem->next = current->next;
    current->next = insertItem;
    return 1;
}

int delete(List_item *listHead, int pid) {
    List_item *current = listHead;
    while (current->next != NULL && current->next->process->pid != pid) {
        current = current->next;
    }
    if (current->next == NULL) {
        // PID not found, deletion unsuccessful
        return 0;
    }
    // Remove the item from the list
    List_item *temp = current->next;
    current->next = current->next->next;
    free(temp->process); // Free the PCB memory
    free(temp); // Free the List_item memory
    return 1;
}

void printList(List_item *listHead) {
    List_item *current = listHead->next;
    while (current != NULL) {
        printf("Process ID: %d, Name: %s\n", current->process->pid, current->process->name);
        current = current->next;
    }
    printf("\n");
}
