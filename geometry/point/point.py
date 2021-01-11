import random
import constants
import lab3


def create_point():
    return [random.randint(0, constants.SCREEN), random.randint(0, constants.SCREEN)]

def create_point_in_pol(p_c, p_s):
    p = create_point()
    while True:
        p = create_point()
        print(p)
        if lab3.point_in_convex_polygon(p_c, p) and lab3.point_out_simple_polygon(p_s, p):
            return p



def create_points(s):
    r = []
    for i in range(s):
        r.append(create_point())
    return r


def create_points_in_polygon(p_c, p_s, s):
    i = 0
    r = []
    while i < s:
        p = create_point()
        if lab3.point_in_convex_polygon(p_c, p) and lab3.point_out_simple_polygon(p_s, p):
            r.append(p)
            i = i + 1
    return r
x = random.randrange(-10, 10, 1)
y = random.randrange(-10, 10, 1)


def move(p):

    # if lab3.point_in_convex_polygon(p_c, [p[0] + x, p[1] + y]) and lab3.point_out_simple_polygon(p_s, [p[0] + x, p[1] + y]):
    p[0] = p[0] + x
    p[1] = p[1] + y
    return p


def move_points(p_c, p_s, r):
    j = 0
    while j < 10:
        i = 0
        while i < len(r):
            x = random.randrange(-1, 1, 1)
            y = random.randrange(-1, 1, 1)
            if lab3.point_in_convex_polygon(p_c, [r[i][0] + x, r[i][1] + y]) and lab3.point_out_simple_polygon(p_s, [r[i][0] + x, r[i][1] + y]):
                r[i][0] = r[i][0] + x
                r[i][1] = r[i][1] + y
                i = i + 1
        return r
