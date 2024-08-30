// Name: Christopher Miller
// Student ID: 20118392
#include "listfuncs.h"

int main() {
    List_item head = {NULL, NULL}; // Initialize the head of the list

    // Create and insert processes
    PCB *p1 = malloc(sizeof(PCB)); p1->pid = 1; strcpy(p1->name, "Process_1");
    List_item *item1 = malloc(sizeof(List_item)); item1->process = p1; item1->next = NULL;
    insert(&head, item1);
    printList(&head);

    PCB *p2 = malloc(sizeof(PCB)); p2->pid = 15; strcpy(p2->name, "Process_15");
    List_item *item2 = malloc(sizeof(List_item)); item2->process = p2; item2->next = NULL;
    insert(&head, item2);
    printList(&head);

    PCB *p3 = malloc(sizeof(PCB)); p3->pid = 7; strcpy(p3->name, "Process_7");
    List_item *item3 = malloc(sizeof(List_item)); item3->process = p3; item3->next = NULL;
    insert(&head, item3);
    printList(&head);

    // Attempt to insert a process with a duplicate PID
    PCB *p4 = malloc(sizeof(PCB)); p4->pid = 7; strcpy(p4->name, "Duplicate_Process");
    List_item *item4 = malloc(sizeof(List_item)); item4->process = p4; item4->next = NULL;
    int result = insert(&head, item4);
    if (result == 0) {
        printf("Insertion of duplicate PID 7 unsuccessful.\n");
        free(p4);
        free(item4);
    }
    printList(&head);

    // Delete processes
    delete(&head, 7);
    printList(&head);

    delete(&head, 20); // Attempt to delete non-existent PID
    printList(&head);

    delete(&head, 1);
    printList(&head);

    return 0;
}
