/**
 * Author: Scott Rincon
 * Description: This program will create a producer consumer buffer that takes items out of and puts items into buffer
 */

#include "buffer.h"
#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <semaphore.h>

#define TRUE 1

buffer_item buffer[BUFFER_SIZE];
/*define semaphores and mutex*/
sem_t empty;
sem_t occupied;
pthread_mutex_t mutex;


int insertPointer = 0, removePointer = 0;

void *producer(void *param);
void *consumer(void *param);

int insert_item(buffer_item item)
{
    /* Acquire Empty Semaphore */
	sem_wait(&empty);
	
	/* Acquire mutex lock to protect buffer */
	pthread_mutex_lock(&mutex);
	buffer[insertPointer] = item;
	if(++insertPointer >= BUFFER_SIZE){
		insertPointer = 0;
	}
	/* Release mutex lock and full semaphore */
	pthread_mutex_unlock(&mutex);
	sem_post(&occupied);

	return 0;
}

int remove_item(buffer_item *item)
{
	/* Acquire Full Semaphore */
	sem_wait(&occupied);

	/* Acquire mutex lock to protect buffer */
	pthread_mutex_lock(&mutex);
	buffer_item tmp = buffer[removePointer];
	
	item = &tmp;
	buffer[removePointer] = 0;
	if(++removePointer >= BUFFER_SIZE){
		removePointer = 0;
	}
	/* Release mutex lock and empty semaphore */
	pthread_mutex_unlock(&mutex);
	sem_post(&empty);
	 

	return 0;
}


int main(int argc, char *argv[])
{
	int sleepTime, producerThreads, consumerThreads;
	int i, j;

	if(argc != 4)
	{
		fprintf(stderr, "Useage: <sleep time> <producer threads> <consumer threads>\n");
		return -1;
	}

	/*call atoi to get arguments */
	sleepTime = atoi(argv[1]);
	producerThreads = atoi(argv[2]);
	consumerThreads = atoi(argv[3]);


	/* Create the producer and consumer threads */
	sem_init(&empty, 0, BUFFER_SIZE);
	sem_init(&occupied, 0, 0);


	pthread_t pt;
	pthread_t ct;
	for(i = 0; i < producerThreads; i++)
	{
		pthread_create(&pt, NULL, producer, NULL);
 
 
	}
	
	for(j = 0; j < consumerThreads; j++)
	{
		pthread_create(&ct, NULL, consumer, NULL);

	}

	/* Sleep for user specified time */
	sleep(sleepTime);
	sem_destroy(&empty);
	sem_destroy(&occupied);
	pthread_exit(NULL);
	return 0;
}

void *producer(void *param)
{
	buffer_item item;

	while(TRUE)
	{
		sleep(2);
		item = rand();
		if(insert_item(item)){
			fprintf(stderr, "Report error condition, Producer\n");
		}

		else{
			printf("producer produced %d\n", item);
		}

	}

}

void *consumer(void *param)
{
	 buffer_item item;

	while(TRUE)
	{
		sleep(4);
		if(remove_item(&item)){
			fprintf(stderr, "Report error condition, Consumer\n");
		}

		else{
			printf("consumer consumed");
		}

	}
}
