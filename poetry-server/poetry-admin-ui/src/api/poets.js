import service from "../utils/request";

export function FindAllPoets() {
  return service({
    url: "/admins/poets",
    method: "get",
  });
}

export function SavePoets(data) {
  return service({
    url: "/admins/poets",
    method: "post",
    data: data,
  });
}
export function UpdatePoets(data) {
  return service({
    url: "/admins/poets",
    method: "put",
    data: data,
  });
}
export function DeletePoetsById(id) {
  return service({
    url: "/admins/poets/" + id,
    method: "delete",
  });
}
