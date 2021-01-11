def x_min(p):
    xmin = p[0][0]
    ind = 0
    for i in range(len(p)):
        if p[i][0] <= xmin:
            xmin = p[i][0]
            ind = i

    return p[ind]


def x_max(p):
    xmax = p[0][0]
    ind = 0
    for i in range(len(p)):
        if p[i][0] >= xmax:
            xmax = p[i][0]
            ind = i
    return p[ind]


def y_min(p):
    ymin = p[0][0]
    ind = 0
    for i in range(len(p)):
        if p[i][1] < ymin:
            ymin = p[i][1]
            ind = i
    return p[ind]


def y_max(p):
    ymax = p[0][0]
    ind = 0
    for i in range(len(p)):
        if p[i][1] > ymax:
            ymax = p[i][1]
            ind = i
    return p[ind]
