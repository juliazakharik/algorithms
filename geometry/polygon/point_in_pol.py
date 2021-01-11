import lab1


def gabarite_test(p, p0):
    xmin = p[0][0]
    xmax = p[0][0]
    ymin = p[0][0]
    ymax = p[0][0]
    for i in range(len(p)):
        if p[i][0] < xmin:
            xmin = p[i][0]
        if p[i][0] > xmax:
            xmax = p[i][0]
        if p[i][1] < ymin:
            ymin = p[i][1]
        if p[i][1] > ymax:
            ymax = p[i][1]
    if p0[0] < xmin or p0[0] > xmax or p0[1] < ymin or p0[1] > ymax:
        return False
    return xmin


def point_in_pol(p, p0):
    if gabarite_test(p, p0):
        q = [gabarite_test(p, p0) - 1, p0[1]]
    else:
        return False
    k = False
    for i in range(len(p)-1):
        if lab1.line_segments_intersect([p0, q], [p[i], p[i+1]]):
            if lab1.point_on_line([p0, q], p[i]):
                if lab1.point_on_line([p0, q], p[i+1]):
                    if lab1.point_position([p0, q], p[i-1]) + lab1.point_position([p0, q], p[i+2]) == 0:
                        k = not k
                else:
                    if lab1.point_position([p0, q], p[i-1]) + lab1.point_position([p0, q], p[i+1]) == 0:
                        k = not k
            else:
                k = not k
    return k


def oct(p1, p2):
    x = p2[0] - p1[0]
    y = p2[1] - p1[1]
    if 0 <= y < x:
        return 1
    if 0 < x <= y:
        return 2
    if 0 <= -x < y:
        return 3
    if 0 < y <= -x:
        return 4
    if 0 <= -y < -x:
        return 5
    if 0 < -x <= -y:
        return 6
    if 0 <= x < -y:
        return 7
    if 0 < -y <= x:
        return 8
    return 0


def octan_test(p, p0):
    s = 0
    if not gabarite_test(p, p0):
        return False
    p.append(p[0])
    for i in range(len(p)-1):
        d1 = oct(p0, p[i])
        d2 = oct(p0, p[i + 1])

        D = d2 - d1
        if D > 4:
            D = D - 8
        if D < -4:
            D = D + 8
        if D == 4 or D == -4:
            det = lab1.det(p0, p[i], p[i + 1])
            if det < 0:
                D = -4
            if det > 0:
                D = 4
            if det == 0:
                return True
        s = s + D
    if s == 8 or s == -8:
        return True
    if s == 0:
        return False
    if s != -8 or s != 0 or s != 8:
        return "error"


def binary_test(p, p0):
    start = 0
    end = len(p) - 1
    while end - start > 1:
        sep = (start + end) // 2
        if lab1.point_position([p[0], p[sep]], p0) == -1:
            end = sep
        else:
            start = sep
    if lab1.point_position([p[0], p[1]], p0) >= 0 or lab1.point_position([p[len(p) - 1], p[0]], p0) >= 0:
        return False, p[start], p[end]
    return not lab1.lines_intersect([p0, p[0]], [p[start], p[end]])


