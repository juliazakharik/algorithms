from point import point, PointClass
import pygame
import constants
import colors
import lab1
from polygon import point_in_pol

hull = []
pygame.init()
sc1 = pygame.display.set_mode((400, 300))
sc1.fill(colors.WHITE)
pygame.display.update()
def dinamic_hull(r, ch):
    p = ch
    p.append(r)
    if len(p) == 0:
        return []
    if len(p) == 1:
        return p
    if len(p) == 2:
        if p[0] == p[1]:
            return p[0]
        else:
            return p
    if len(p) == 3:
        if (p[2] == p[0] or p[2] == p[1]) and p[0] != p[1]:
            return [p[0], p[1]]
        if p[0] == p[1] == p[2]:
            return p[0]
        if (p[0] != p[1] != p[2]) and lab1.point_position([p[0], p[1]], p[2]) == 0:
            if lab1.vector_mult(p[0], p[1]) * lab1.vector_mult(p[0], p[2]) < 0:
                return [p[1], p[2]]
            if lab1.vector_mult(p[2], p[1]) * lab1.vector_mult(p[2], p[0]) < 0:
                return [p[0], p[1]]
            if lab1.vector_mult(p[1], p[0]) * lab1.vector_mult(p[1], p[2]) < 0:
                return [p[0], p[2]]
        else:
            if lab1.point_left([p[0], p[1]], p[2]):
                return [p[0], p[1], p[2]]
            else:
                return [p[0], p[2], p[1]]
    temp = []
    is_first = False
    i_min = 0
    i_max = 0
    del p[-1]
    p.append(p[0])

    if point_in_pol.point_in_pol(p, r):
        del p[-1]
        return p
    for i in range(len(p)-1):
        if not lab1.point_left([p[i], p[i+1]], r):
            if is_first:
                i_max = i +1
            else:
                is_first = True
                i_min = i
    p_c = p.copy()
    if i_max - i_min>1:
        for i in range(i_min + 1, i_max):
            del p[i_min + 1]
    p.insert(i_min + 1, r)
    del p[-1]
    if len(p)==2:
        del p_c[0]
        del p_c[-1]
        pygame.draw.polygon(sc1, colors.BLACK, p_c, 1)
        p_c = dinamic_hull(r,p_c)

        return p_c
    else:
        return p
    return p












def draw():
    pygame.init()
    sc = pygame.display.set_mode((400, 300))
    sc.fill(colors.WHITE)
    pygame.display.update()
    ch = []

    while 1:
        for i in pygame.event.get():
            if i.type == pygame.QUIT:
                exit()
            if i.type == pygame.MOUSEBUTTONDOWN:
                if i.button == 1:
                    pygame.draw.circle(sc, colors.BLACK, i.pos, 2)
                    sc.fill(colors.WHITE)
                       ch = dinamic_hull(i.pos, ch)
                    if len(ch) == 1:
                        pygame.draw.circle(sc, colors.BLACK, ch[0], 2, 1)
                    else:
                        temp = ch
                        for i in range((len(temp)) - 1):
                            pygame.draw.line(sc, colors.BLACK, temp[i], temp[i+1], 1)
                        pygame.draw.line(sc, colors.BLACK, temp[-1], temp[0], 1)
                    pygame.display.update()
        pygame.time.delay(20)


def main():
    p = point.create_points(50)
    draw()

draw()