from lab1 import *
from polygon import polygon
from point import point
import pygame
import colors
import constants


# def point_intersection(p11, p12, p21, p22):
#     a1 = (p12[0] - p11[0])
#     a2 = (p22[0] - p21[0])
#     b1 = (p12[0]*p11[1] - p11[0]*p12[1])
#     b2 = (p22[0]*p21[1] - p21[0]*p22[1])
#     c1 = (p11[1] - p12[1])
#     c2 = (p21[1] - p22[1])
#     x = (a1*b2 - a2*b1)/(c2*a1 - c1*a2)
#     y = (b1 - c1*x)/a1
#     return [x, y]


def gcd(a, b):
    assert a > 0
    assert b > 0
    while b:
        a, b = b, a % b
    return a


def mcd(a, b):
    assert a > 0
    assert b > 0
    return (a / gcd(a, b)) * b
def point_intersection(line1, line2):
    assert len(line1) == 2
    assert len(line2) == 2
    assert len(line1[0]) == 2
    assert len(line1[1]) == 2
    assert len(line2[0]) == 2
    assert len(line2[1]) == 2

    x = []
    y = []

    A1 = line1[1][1] - line1[0][1]
    B1 = line1[0][0] - line1[1][0]
    C1 = -line1[0][0] * line1[1][1] + line1[0][1] * line1[1][0]
    A2 = line2[1][1] - line2[0][1]
    B2 = line2[0][0] - line2[1][0]
    C2 = -line2[0][0] * line2[1][1] + line2[0][1] * line2[1][0]
    if A1 == 0:
        y = line1[0][1]
    if A2 == 0:
        y = line2[0][1]
    if B1 == 0:
        x = line1[0][0]
    if B2 == 0:
        x = line2[0][0]
    if x != [] and y != []: # lines - (horizontal and vertical)
        return [x, y]
    else:
        if x != []: # one line horizontal
            if B1 == 0:
                return [x, -(C2 + A2 * x) / B2]
            else:
                return [x, -(C1 + A1 * x) / B1]
        elif y != []: # one line vertical
            if A1 == 0:
                return [-(B2 * y + C2) / A2, y]
            else:
                return [-(B1 * y + C1) / A1, y]
        else: # 2 lines neither vertical not horizontal
            if A1 < 0:
                A1 = -A1
                B1 = -B1
                C1 = -C1
            if A2 < 0:
                A2 = -A2
                B2 = -B2
                C2 = -C2
            A = mcd(A1, A2)
            B1 = B1 * A / A1
            C1 = C1 * A / A1
            A1 = A
            B2 = B2 * A / A2
            C2 = C2 * A / A2
            A2 = A

            y = -(C1 - C2) / (B1 - B2)
            x = -(B1 * y + C1) / A1
            return [x, y]

def parameter_intersection(l, l0):
    a = line_length(l)
    x = point_intersection([l[0], l[1]], [l0[0], l0[1]])
    b = line_length([l[0], x])
    return b/a


def cyrus_beck(p, l):
    t0 = 0
    t1 = 1
    # p.append(p[0])
    for i in range(len(p)-1):
        if lines_intersect([p[i], p[i+1]], l):
            # if vector_mult([l[1][0]-l[0][0], l[1][1]-l[0][1]],
            #                normal([p[i+1][0]-p[i][0], p[i+1][1]-p[i][1]])) > 0:
            if ((p[i+1][1]-p[i][1])*(l[1][0]-l[0][0]) - (p[i+1][0]-p[i][0])*l[1][1]-l[0][1]) > 0:
                t0 = max(t0, parameter_intersection([p[i], p[i+1]], l))
            else:
                t1 = min(t1, parameter_intersection([p[i], p[i+1]], l))
    if t0 > t1:
        return False
    if t0 == t1:
        return [[float(l[0][0]*(1-t0) + l[1][0]*t0), float(l[0][1]*(1-t0) + l[1][1]*t0)]]
    if t0 < t1:
        return [[float(l[0][0]*(1-t0) + l[1][0]*t0), float(l[0][1]*(1-t0) + l[1][1]*t0)], [float(l[0][0]*(1-t1) + l[1][0]*t1), float(l[0][1]*(1-t1) + l[1][1]*t1)]]




def draw(p, l, p0):
    pygame.init()
    sc = pygame.display.set_mode((constants.SCREEN, constants.SCREEN))
    sc.fill(colors.WHITE)
    pygame.display.update()

    cr = False
    while not cr:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                cr = True
        pygame.draw.polygon(sc, colors.BLACK, p, 1)
        pygame.draw.line(sc, colors.BLACK, l[0], l[1], 1)
        pygame.font.init()
        myfont = pygame.font.SysFont('Comic Sans MS', 10)
        textsurface = myfont.render('end', False, colors.BLACK)
        sc.blit(textsurface, (p[1]))
        textsurface = myfont.render('start', False, colors.BLACK)
        sc.blit(textsurface, (p[0]))
        if p0 and len(p0)==1:
            pygame.draw.circle(sc, colors.BLUE, [int(p0[0][0]), int(p0[0][1])], 3)
        if p0 and len(p0)==2:
            pygame.draw.circle(sc, colors.BLUE, [int(p0[0][0]), int(p0[0][1])], 3)
            pygame.draw.circle(sc, colors.BLUE, [int(p0[1][0]), int(p0[1][1])], 3)
        pygame.display.flip()
    pygame.quit()


def main():
    p = polygon.create_convex_polygon(6)
    l = point.create_points(2)
    # p = [[100, 100], [110, 200], [200, 200], [210, 100]]
    # l = [[50, 20], [150, 160]]
    p0 = cyrus_beck(p, l)
    print(p0)
    draw(p, l, p0)
    p.reverse()
    p0 = cyrus_beck(p, l)
    print(p0)

main()
