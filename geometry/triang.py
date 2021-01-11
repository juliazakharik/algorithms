from polygon import point_in_pol
import pygame
import colors
import constants
from point import point
from hull import quick_hull
import Triangle
import lab1
triang = []
on = True

def sort(p, on1):
    if on1:
        p.sort(key=lambda x: x[0])
        on = False
    else:
        p.sort(key=lambda x: x[1])
        on = True


def condition(a, b, c, p0):
    if (((p0[0] - a[0])(p0[1] - c[1]) - (p0[0] - c[0])(p0[1] - a[1]))((b[0] - a[0])(b[0] - c[0]) - (b[1] - a[1])(b[1] - c[1])) + ((p0[0] - a[0])(p0[0] - c[0]) + (p0[1] - a[1])(p0[1] - c[1]))((b[0] - a[0])(b[1] - c[1]) - (b[0] - c[0])(b[1] - a[1]))) >=0:
        return True
    return False

tr = []
tr_arr = []
qh = []



def delone(p):
    p.sort(key=lambda x: x[0])
    t1 = Triangle.Triangle([p[0], p[1], p[2]])
    tr.append(t1)
    tr_arr.append(p[0])
    tr_arr.append(p[1])
    tr_arr.append(p[2])
    is_first = False
    i_min = 0
    i_max = 0
    for i in range(len(p)-1):
        if not lab1.point_left([p[i], p[i+1]], p):
            if is_first:
                i_max = i +1
            else:
                is_first = True
                i_min = i
    print(i_max, i_min)
    #for i in range(2, len(p)):





def triang4(p):
    is_in = False
    ind = 0
    for i in range(len(p)):
        if point_in_pol.point_in_pol(p, p[i]):
            is_in = True
            ind = i
            break
    if is_in:
        if ind == 0:
            triang.append([p[0], p[1], p[2]])
            triang.append([p[0], p[2], p[3]])
            triang.append([p[0], p[1], p[3]])
        if ind == 1:
            triang.append([p[1], p[3], p[2]])
            triang.append([p[1], p[0], p[3]])
            triang.append([p[1], p[0], p[2]])
        if ind == 2:
            triang.append([p[2], p[1], p[3]])
            triang.append([p[2], p[0], p[3]])
            triang.append([p[2], p[1], p[0]])
        if ind == 3:
            triang.append([p[3], p[1], p[2]])
            triang.append([p[3], p[2], p[0]])
            triang.append([p[3], p[1], p[0]])
    else:
        triang.append([p[0], p[1], p[2]])
        triang.append([p[2], p[3], p[0]])


def tr(p):
    # p - set of points
    if len(p) == 3:
        triang.append([p[0], p[1], p[2]])

    elif len(p) == 4:
        triang4(p)
    elif len(p) == 8:
        sort(p, on)
        tr([p[0], p[1], p[2], p[3]])
        tr([p[4], p[5], p[6], p[7]])
    elif len(p) < 12:
        sort(p, on)
        tr([p[0], p[1], p[2]])
        tmp = []
        for i in range(2, len(p)):
            tmp.append(p[i])
        tr(tmp)
    elif len(p) >= 12:
        sort(p, on)
        tmp1 = []
        for i in range(int(len(p)/2)):
            tmp1.append(p[i])
        tmp2 = []
        for i in range(int(len(p)/2), len(p)):
            tmp2.append(p[i])
        tr(tmp1)
        tr(tmp2)




def draw(p):

    pygame.init()
    pygame.font.init()
    myfont = pygame.font.SysFont('Comic Sans MS', 10)
    screen = pygame.display.set_mode((constants.SCREEN, constants.SCREEN))
    pygame.display.update()
    screen.fill(colors.WHITE)
    tr(p)
    print(triang)
    clock = pygame.time.Clock()
    while 1:
        for i in pygame.event.get():
            if i.type == pygame.QUIT:
                exit()
            if i.type == pygame.MOUSEBUTTONDOWN:
                if i.button == 1:

                    screen.fill(colors.WHITE)
                    for i in range(len(triang)):
                        pygame.draw.polygon(screen, colors.BLACK, triang[i], 1)

                    pygame.display.update()
        pygame.time.delay(20)


def main():
    p =point.create_points(4)
    draw(p)

main()