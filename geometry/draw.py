import random

import pygame
import colors
import constants
import lab1
import lab3
from point import point, PointClass
from point.PointClass import Point
from polygon import polygon
from hull.quick_hull import perimter
from diameter import diameter

pygame.init()
pygame.font.init()
myfont = pygame.font.SysFont('Comic Sans MS', 10)
screen = pygame.display.set_mode((constants.SCREEN, constants.SCREEN))
pygame.display.update()
screen.fill(colors.WHITE)


def draw_point(p):
    pygame.draw.circle(screen, colors.BLACK, p, 2, 1)


def draw_points(r):
    for i in range(len(r)):
        draw_point(r[i])


def draw_line(l):
    pygame.draw.line(screen, colors.BLACK, l[0], l[1], 1)


def draw_polygon(p):
    pygame.draw.polygon(screen, colors.BLACK, p, 1)


def draw_arrow(l):
    draw_line(l)
    textsurface = myfont.render('end', False, colors.BLUE)
    screen.blit(textsurface, (l[1]))
    textsurface = myfont.render('start', False, colors.BLUE)
    screen.blit(textsurface, (l[0]))


def draw_point_line(l, p):
    draw_point(p)
    draw_arrow(l)
    run()


def draw_lines(l1, l2):
    draw_line(l1)
    draw_line(l2)
    run()


def draw_polygon_lab(p):
    draw_polygon(p)
    draw_arrow([p[0], p[1]])
    run()


def draw_lab_2(p, p0):
    draw_polygon(p)
    draw_point(p0)
    run()


def draw_lab_3(p1, p2, r):
    points = []
    for i in range(len(p1) - 1):
        del r[r.index(p1[i])]
    for i in range(len(r)):
        points.append(PointClass.Point(r[i][0], r[i][1]))
    clock = pygame.time.Clock()
    cr = False
    while not cr:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                cr = True
        clock.tick(20)
        screen.fill(colors.WHITE)
        draw_polygon(p1)
        draw_polygon(p2)
        for i in points:
            i.draw(screen)
            i.collised_with_simple(p2)
            i.collised_with_convex(p1)
            i.move()
        pygame.display.flip()
    pygame.quit()


def draw_move_points(p1, p2, r):
    cr = False
    while not cr:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                cr = True
        for i in range(len(r)):
            draw_point(r[i])
        r = point.move_points(p1, p2, r)
        pygame.display.update()


def draw_lines(p):
    for i in range((len(p)) - 1):
        draw_line([p[i], p[i+1]])


def draw_lab_4(p1, r):
    clock = pygame.time.Clock()
    lines = polygon.grekhem(r)
    cr = False
    i = -1
    while not cr:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                cr = True
        clock.tick(5)
        screen.fill(colors.WHITE)
        if i < len(lines)-1:
            i = i+1
        else:
            break
        draw_lines(lines[i])
        draw_points(r)
        pygame.display.flip()
    pygame.quit()


def draw_lab_6(r):
    points = []

    for i in range(len(r)):
        points.append(PointClass.Point(r[i][0], r[i][1]))
    clock = pygame.time.Clock()
    cr = False
    while not cr:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                cr = True
        clock.tick(40)
        screen.fill(colors.WHITE)
        for i in points:
            i.draw(screen, colors.BLACK)
            i.move()
            draw_polygon(quickhull(points))
        pygame.display.flip()
    pygame.quit()


def draw_lab5(p):

    r = point.create_points(15)
    points = []

    for i in range(len(r)):
        points.append(PointClass.Point(r[i][0], r[i][1]))
    clock = pygame.time.Clock()
    cr = False
    while not cr:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                cr = True

        screen.fill(colors.WHITE)
        clock.tick(20)

        for i in points:
            i.draw(screen)

            i.move()
        h = polygon.jarves(points)
        if perimter(h) > 1500:
            for i in points:
                i.move_back()
        pygame.draw.polygon(screen, colors.BLACK, h, 1)
        i1, i2 = diameter(h)
        draw_arrow([h[i1], h[i2]])
        pygame.display.flip()
    pygame.quit()




def draw_lab_10(p):
    r = point.create_points(15)
    points = []

    for i in range(len(r)):
        points.append(PointClass.Point(r[i][0], r[i][1]))
    clock = pygame.time.Clock()
    cr = False
    while not cr:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                cr = True

        screen.fill(colors.WHITE)
        clock.tick(20)

        for i in points:
            i.draw(screen)

            i.move()
        h = polygon.jarves(points)
        if perimter(h) > 1500:
            for i in points:
                i.move_back()
        pygame.draw.polygon(screen, colors.BLACK, h, 1)
        i1, i2 = diameter(h)
        draw_arrow([h[i1], h[i2]])
        points[i1].move_back()
        points[i2].move_back()
        pygame.display.flip()
    pygame.quit()






def run():
    cr = False
    while not cr:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                cr = True
        pygame.display.update()
