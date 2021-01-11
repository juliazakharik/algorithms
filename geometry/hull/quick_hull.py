import lab1
from polygon import gabarite_polygon
from point import point
import pygame
import constants
import colors
from point import PointClass


hull = []

#r = point.create_points(15)


def qh_left(r, xmin, xmax):
    i_max_area = -1
    max_area = 0
    for i in range(len(r)):
        #<l >r
        if lab1.area(xmin, xmax, r[i]) < max_area:
            max_area = lab1.area(xmin, xmax, r[i])
            i_max_area = i
    if i_max_area == -1:
        hull.append(xmax)
    else:
        qh_left(r, xmin, r[i_max_area])
        qh_left(r, r[i_max_area], xmax)


def qh_right(r, xmin, xmax):
    i_max_area = -1
    max_area = 0
    for i in range(len(r)):
        #<l >r
        if lab1.area(xmin, xmax, r[i]) > max_area:
            max_area = lab1.area(xmin, xmax, r[i])
            i_max_area = i
    if i_max_area == -1:
        hull.append(xmax)
    else:
        qh_left(r, xmin, r[i_max_area])
        qh_left(r, r[i_max_area], xmax)


def quick_hull(p):
    r = []

    for i in range(len(p)-1):
        r.append([])
        r[i].append(PointClass.Point.get_x(p[i]))
        r[i].append(PointClass.Point.get_y(p[i]))

    xmin = gabarite_polygon.x_min(r)
    xmax = gabarite_polygon.x_max(r)
    Sl = []
    Sr = []
    for i in range(len(r)):
        # xmin -> xmax left
        if lab1.point_left([xmin, xmax], r[i]):
            Sl.append(r[i])
        else:
            if not lab1.point_left([xmin, xmax], r[i]):
                Sr.append(r[i])
    qh_left(Sl, xmin, xmax)
    qh_right(Sr, xmin, xmax)
    del hull[-1]

def perimter(r):
    per = 0
    for i in range(len(r)-1):
        per = per + lab1.line_length([r[i], r[i+1]])
    return per



def draw():
    pygame.init()
    pygame.font.init()
    myfont = pygame.font.SysFont('Comic Sans MS', 10)
    screen = pygame.display.set_mode((constants.SCREEN, constants.SCREEN))
    pygame.display.update()
    screen.fill(colors.WHITE)

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
        f_h = hull
        for i in points:
            i.draw(screen)

            i.move()
        quick_hull(points)

        pygame.draw.polygon(screen, colors.BLACK, hull, 1)
        if perimter(hull) > 1500:
            for i in points:
                i.move_back()
        hull.clear()
        pygame.display.flip()
    pygame.quit()



