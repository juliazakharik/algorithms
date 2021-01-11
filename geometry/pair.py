from lab1 import line_length as length
from point import point, PointClass
import pygame
import constants, colors



points_move = []
pygame.init()
pygame.font.init()
myfont = pygame.font.SysFont('Comic Sans MS', 10)
screen = pygame.display.set_mode((constants.SCREEN, constants.SCREEN))
sc = pygame.display.set_mode((constants.SCREEN, constants.SCREEN))
pygame.display.update()
screen.fill(colors.WHITE)

clock = pygame.time.Clock()


def min2(a, b):
    if a < b:
        return a
    else:
        return b
def pair(p):
    x = p.copy()
    y = p.copy()
    x.sort(key=lambda p: p[0])
    y.sort(key=lambda p: p[1])
    return f(x, y)
res = []
def f(X, Y):
    if len(X) <= 3:
        d = constants.SCREEN
        for i in range(len(X)):
            for j in range(i+1, len(X)):
                if length([X[i], X[j]]) < d:
                    d = length([X[i], X[j]])
                    if len(res) != 0:
                        res.clear()
                    res.append(X[i])
                    res.append(X[j])
        return d
    sep = len(X) // 2
    p0 = X[sep]
    XL = X[0: sep]
    XR = X[sep: len(X)]
    YL = []
    YR = []
    for i in range(len(Y)):
        if Y[i][0] >= p0[0]:
            YR.append(Y[i])
        else:
            YL.append(Y[i])
    dl = f(XL, YL)
    dr = f(XR, YR)
    d = min2(dl, dr)
    Ydel = []
    for i in range(len(Y)):
        if abs(Y[i][0] - p0[0]) < d:
            Ydel.append(Y[i])

    for i in range(len(Ydel)):
        j = i
        k = 7
        if len(Ydel) - i < 7:
            k = len(Ydel) - i
        while j < i + k:
            if i != j and length([Ydel[i], Ydel[j]]) <= d:
                d = length([Ydel[i], Ydel[j]])
                if len(res) != 0:
                    res.clear()
                res.append(Ydel[i])
                res.append(Ydel[j])
            j += 1
    return d


def draw():
    p = point.create_points(20)

    for i in p:
        points_move.append(PointClass.Point(i[0], i[1]))
    print(pair(p))
    run = True
    while run:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                run = False

        screen.fill(colors.WHITE)
        clock.tick(30)
        pygame.draw.line(screen, colors.BLACK, res[0], res[1], 1)

        for i in range(len(points_move)):
            points_move[i].draw(screen)

        pygame.display.flip()

    pygame.quit()


draw()