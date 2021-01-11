import pygame
import random


pygame.init()
screen = pygame.display.set_mode((510,510))
pygame.display.update()
white = (255,255,255)
black = (0,0,0)
screen.fill(white)
p = [random.randint(0, 500), random.randint(0, 500)]
p1 = [random.randint(0, 500), random.randint(0, 500)]
pygame.draw.circle(screen,black,p,2)
pygame.draw.line(screen,black,p,p1,1)
cr = False
while not cr:
    for event in pygame.event.get():
        if event.type==pygame.QUIT:
            cr=True
    pygame.display.update()