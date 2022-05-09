import service from "../utils/request";

export function FindAllpoemList() {
  return service({
    url: "/admins/poemList",
    method: "get",
  });
}

export function SavepoemList(data) {
  return service({
    url: "/admins/poemList",
    method: "post",
    data: data,
  });
}
export function UpdatepoemList(data) {
  return service({
    url: "/admins/poemList",
    method: "put",
    data: data,
  });
}
export function DeletepoemListById(id) {
  return service({
    url: "/admins/poemList/" + id,
    method: "delete",
  });
}
